package com.turkcell.rentalservice.services.concretes;

import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repositories.RentalRepository;
import com.turkcell.rentalservice.services.abstracts.RentalService;
import java.time.LocalDate;
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

  @Override
  public String submitRental(String inventoryCode) {
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
    if (state) {
      Rental rental =
          Rental.builder().rentalDate(LocalDate.now()).inventoryCode(inventoryCode).build();
      rentalRepository.save(rental);
      kafkaTemplate.send(
          "notificationTopic",
          " Tebrikler bir arac kiraladiniz. Araci teslim almak icin bugun saat 22:00'a kadar muracaat edin. ");
      return "Arac kiralandi";
    } else return "Arac kiralamaya uygun degil";
  }
}
