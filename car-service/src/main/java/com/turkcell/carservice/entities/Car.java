package com.turkcell.carservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "cars")
public class Car {
  @Id private String objectId;
  private String inventoryCode;
  private String brand;
  private String model;
  private String colour;
  private short modelYear;
  private Double dailyPrice;

  @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL)
  private List<Image> images = new ArrayList<>();

  private Boolean state;
}
