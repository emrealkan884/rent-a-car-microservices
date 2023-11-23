package com.turkcell.customerservice.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAddRequest {
  private String name;
  private String lastName;
  private String username;
  private String password;
  private String email;
}
