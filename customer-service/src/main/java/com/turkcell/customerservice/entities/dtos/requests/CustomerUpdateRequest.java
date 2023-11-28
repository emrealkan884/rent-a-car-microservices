package com.turkcell.customerservice.entities.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateRequest {
  private String name;
  private String lastName;

  @NotBlank(message = "Kullanici adi bos birakilamaz.")
  private String username;

  @NotBlank(message = "Kullanici sifresi bos birakilamaz.")
  @Size(min = 8, max = 20, message = "Parola en az 8 hane ve en fazla 20 hane olabilir")
  private String password;

  @NotBlank(message = "Kullanici emaili bos birakilamaz.")
  @Email(
      message = "Email gecerli degil.",
      regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;
}
