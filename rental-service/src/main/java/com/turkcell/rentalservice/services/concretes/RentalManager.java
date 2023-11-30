package com.turkcell.rentalservice.services.concretes;

import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.entities.dtos.requests.RentalUpdateRequest;
import com.turkcell.rentalservice.entities.dtos.requests.SubmitRentalDto;
import com.turkcell.rentalservice.entities.dtos.responses.RentalGetResponse;
import com.turkcell.rentalservice.entities.dtos.responses.RentalUpdateResponse;
import com.turkcell.rentalservice.repositories.RentalRepository;
import com.turkcell.rentalservice.services.abstracts.RentalService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

  private final RentalRepository rentalRepository;
  private final WebClient.Builder webClientBuilder;
  private final ModelMapper modelMapper;
  private final MessageSource messageSource;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public String submitRental(SubmitRentalDto submitRentalDto) {
    try {
      boolean isCarAvailable = checkCarState(submitRentalDto.getInventoryCode());
      double dailyPrice = getDailyPrice(submitRentalDto.getInventoryCode());
      double customerBalance = getCustomerBalance(submitRentalDto.getCustomerId());
      double totalRentalPrice =
          dailyPrice * (ChronoUnit.DAYS.between(LocalDate.now(), submitRentalDto.getEndDate()));

      if (isCarAvailable && totalRentalPrice <= customerBalance) {
        Rental rental =
            Rental.builder()
                .rentalDate(LocalDate.now())
                .inventoryCode(submitRentalDto.getInventoryCode())
                .customerId(submitRentalDto.getCustomerId())
                .endDate(submitRentalDto.getEndDate())
                .build();

        customerBalanceDown(submitRentalDto.getCustomerId(), totalRentalPrice);
        // Aracın durumunu güncelle
        updateCarState(submitRentalDto.getInventoryCode());
        // Kafkaya bildirim gönder
        sendNotification();
        // Kiralamayı kaydet
        rentalRepository.save(rental);
        return messageSource.getMessage("CarIsRented", null, LocaleContextHolder.getLocale());
      } else {
        return messageSource.getMessage("CarNotSuitable", null, LocaleContextHolder.getLocale());
      }
    } catch (Exception e) {
      return messageSource.getMessage("ThereIsAError", null, LocaleContextHolder.getLocale())
          + e.getMessage();
    }
  }

  @Override
  public void delete(int id) {
    rentalRepository.deleteById(id);
  }

  @Override
  public RentalUpdateResponse update(int id, RentalUpdateRequest request) {
    Rental rental = rentalRepository.getReferenceById(id);
    // ornegin rental.setRentalDate(request.getRentalDate) gibi degerleri otomatik atiyor.
    modelMapper.map(request, rental);
    rental = rentalRepository.save(rental);
    RentalUpdateResponse rentalUpdateResponse = modelMapper.map(rental, RentalUpdateResponse.class);
    return rentalUpdateResponse;
  }

  @Override
  public RentalGetResponse getById(int id) {
    Rental rental = rentalRepository.getReferenceById(id);
    RentalGetResponse getByIdRentalDto = modelMapper.map(rental, RentalGetResponse.class);
    return getByIdRentalDto;
  }

  @Override
  public List<RentalGetResponse> getAll() {
    List<Rental> rentals = rentalRepository.findAll();
    List<RentalGetResponse> rentalGetResponses =
        rentals.stream().map(item -> modelMapper.map(item, RentalGetResponse.class)).toList();
    return rentalGetResponses;
  }

  private boolean checkCarState(String inventoryCode) {
    Boolean state =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://car-service/api/cars/getStateByInventoryCode",
                (uriBuilder) -> uriBuilder.queryParam("inventoryCode", inventoryCode).build())
            .retrieve() // başka bir servisten gelen veriyi almak üzere bir HTTP isteği başlatır.
            .bodyToMono(
                Boolean.class) // HTTP yanıtındaki gövdeyi bir Mono nesnesine dönüştürmek için
            // kullanılır.
            .block(); // HTTP isteği tamamlanana kadar bekler
    return state;
  }

  private double getDailyPrice(String inventoryCode) {
    Double dailyPrice =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://car-service/api/cars/getDailyPriceByInventoryCode",
                (uriBuilder -> uriBuilder.queryParam("inventoryCode", inventoryCode).build()))
            .retrieve()
            .bodyToMono(Double.class)
            .block();
    return dailyPrice;
  }

  private double getCustomerBalance(int customerId) {
    Double customerBalance =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://customer-service/api/customers/getBalanceByCustomerId",
                (uriBuilder -> uriBuilder.queryParam("customerId", customerId).build()))
            .retrieve()
            .bodyToMono(Double.class)
            .block();
    return customerBalance;
  }

  private void customerBalanceDown(int customerId, double dailyPrice) {
    webClientBuilder
        .build()
        .post()
        .uri(
            "http://customer-service/api/customers/balanceDown",
            (uriBuilder ->
                uriBuilder
                    .queryParam("customerId", customerId)
                    .queryParam("balance", dailyPrice)
                    .build()))
        .retrieve()
        .bodyToMono(Double.class)
        .block();
  }

  private void updateCarState(String inventoryCode) {
    webClientBuilder
        .build()
        .post()
        .uri(
            "http://car-service/api/cars/updateState",
            (uriBuilder ->
                uriBuilder
                    .queryParam("inventoryCode", inventoryCode)
                    .queryParam("state", false)
                    .build()))
        .retrieve()
        .bodyToMono(Boolean.class)
        .block();
  }

  private void sendNotification() {
    kafkaTemplate.send(
        "notificationTopic",
        messageSource.getMessage("CarRentedMessage", null, LocaleContextHolder.getLocale()));
  }
}
