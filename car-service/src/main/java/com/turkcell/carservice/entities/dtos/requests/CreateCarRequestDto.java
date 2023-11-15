package com.turkcell.carservice.entities.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCarRequestDto {
    private String inventoryCode;
    private String brand;
    private String model;
    private String colour;
    private short modelYear;
    private Double dailyPrice;
    private Boolean state;

}
