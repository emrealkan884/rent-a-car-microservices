package com.turkcell.carservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(value = "cars")
public class Car {
    @Id
    private String objectId;
    private String inventoryCode;
    private String brand;
    private String model;
    private String colour;
    private short modelYear;
    private Double dailyPrice;
    private String photo;
    private Boolean state;
}
