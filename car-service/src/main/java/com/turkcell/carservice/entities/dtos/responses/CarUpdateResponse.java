package com.turkcell.carservice.entities.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarUpdateResponse {
  private String inventoryCode;
  private String brand;
  private String model;
  private String colour;
  private short modelYear;
  private Double dailyPrice;
  private Boolean state;
}
