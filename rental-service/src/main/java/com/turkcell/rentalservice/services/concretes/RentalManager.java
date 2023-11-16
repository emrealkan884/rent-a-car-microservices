package com.turkcell.rentalservice.services.concretes;

import com.turkcell.rentalservice.repositories.RentalRepository;
import com.turkcell.rentalservice.services.abstracts.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {

  private final RentalRepository rentalRepository;
  private final WebClient.Builder webClientBuilder;

  @Override
  public String submitRental(String inventoryCode) {
    Boolean state =
        webClientBuilder
            .build()
            .get()
            .uri(
                "http://car-service/api/cars/getState",
                (uriBuilder) -> uriBuilder.queryParam("inventoryCode", inventoryCode).build())
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();
    if (state) return "Arac kiralamaya uygun";
    else return "Arac kiralamaya uygun degil";
  }
}
