package com.turkcell.carservice.entities.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageAddResponse {
  private String name;
  private String url;
  private String carInventoryCode;
}
