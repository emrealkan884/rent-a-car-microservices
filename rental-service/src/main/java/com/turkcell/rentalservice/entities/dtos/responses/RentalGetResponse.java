package com.turkcell.rentalservice.entities.dtos.responses;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalGetResponse {
  LocalDate rentalDate;
  private String inventoryCode;
}
