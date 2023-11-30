package com.turkcell.rentalservice.entities.dtos.requests;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitRentalDto {

  private String inventoryCode;
  private int customerId;
  private LocalDate endDate;
}
