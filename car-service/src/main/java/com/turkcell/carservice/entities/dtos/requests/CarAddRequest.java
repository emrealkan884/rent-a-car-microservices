package com.turkcell.carservice.entities.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarAddRequest {
  @NotBlank(message = "{inventoryCodeCannotBeLeftBlank}")
  @Size(min = 2, message = "{inventoryCodeCannotBeSmallerThanTwoDigits}")
  private String inventoryCode;

  @NotBlank(message = "{brandCannotBeLeftBlank}")
  private String brand;

  @NotBlank(message = "{modelCannotBeLeftBlank}")
  private String model;

  private String colour;

  @NotNull(message = "{modelYearCannotBeLeftBlank}")
  private short modelYear;

  @NotNull(message = "{dailyPriceCannotBeLeftBlank}")
  @Min(value = 500, message = "{dailPriceMinimumShouldBeFiveHundred.}")
  private Double dailyPrice;

  private Boolean state;
}
