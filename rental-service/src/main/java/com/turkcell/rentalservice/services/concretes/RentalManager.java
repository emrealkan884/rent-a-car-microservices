package com.turkcell.rentalservice.services.concretes;

import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.entities.dtos.requests.RentalUpdateRequest;
import com.turkcell.rentalservice.entities.dtos.responses.GetRentalDto;
import com.turkcell.rentalservice.repositories.RentalRepository;
import com.turkcell.rentalservice.services.abstracts.RentalService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

  private final RentalRepository rentalRepository;
  private final WebClient.Builder webClientBuilder;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public String submitRental(String inventoryCode, int customerId) {
    try {
      boolean isCarAvailable = checkCarState(inventoryCode);
      double dailyPrice = getDailyPrice(inventoryCode);
      double customerBalance = getCustomerBalance(customerId);

      if (isCarAvailable && dailyPrice <= customerBalance) {
        Rental rental =
            Rental.builder().rentalDate(LocalDate.now()).inventoryCode(inventoryCode).build();

        customerBalanceDown(customerId, dailyPrice);
        // Aracın durumunu güncelle
        updateCarState(inventoryCode);
        // Kafkaya bildirim gönder
        sendNotification();
        // Kiralamayı kaydet
        rentalRepository.save(rental);
        return "Arac kiralandi";
      } else {
        return "Arac kiralamaya uygun degil";
      }
    } catch (Exception e) {
      return "Bir hata olustu: " + e.getMessage();
    }
  }

  @Override
  public void delete(int id) {
    rentalRepository.deleteById(id);
  }

  @Override
  public GetRentalDto update(int id, RentalUpdateRequest request) {
    Rental rental = rentalRepository.getReferenceById(id);
    rental.setRentalDate(request.getRentalDate());
    rental.setInventoryCode(request.getInventoryCode());
    GetRentalDto getRentalDto =
        GetRentalDto.builder()
            .inventoryCode(rental.getInventoryCode())
            .rentalDate(rental.getRentalDate())
            .build();
    return getRentalDto;
  }

  @Override
  public GetRentalDto getById(int id) {
    Rental rental = rentalRepository.getReferenceById(id);
    GetRentalDto getByIdRentalDto =
        GetRentalDto.builder()
            .inventoryCode(rental.getInventoryCode())
            .rentalDate(rental.getRentalDate())
            .build();
    return getByIdRentalDto;
  }

  @Override
  public List<GetRentalDto> getAll() {
    List<Rental> rentals = rentalRepository.findAll();
    List<GetRentalDto> getRentalDtos = new ArrayList<>();
    GetRentalDto getRentalDto = new GetRentalDto();
    for (Rental rental : rentals) {
      getRentalDto.setRentalDate(rental.getRentalDate());
      getRentalDto.setInventoryCode(rental.getInventoryCode());
      getRentalDtos.add(getRentalDto);
    }
    return getRentalDtos;
  }

  private boolean checkCarState(String inventoryCode) {
    Boolean state =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://car-service/api/cars/getStateByInventoryCode",
                (uriBuilder) -> uriBuilder.queryParam("inventoryCode", inventoryCode).build())
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();
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
        " Tebrikler bir arac kiraladiniz. Araci teslim almak icin bugun saat 22:00'a kadar muracaat edin. ");
  }
}
