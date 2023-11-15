package com.turkcell.carservice.entities.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedCarResponseDto {
    private String brand;
    private String model;
    private String colour;
    private short modelYear;
    private Boolean state;
}
