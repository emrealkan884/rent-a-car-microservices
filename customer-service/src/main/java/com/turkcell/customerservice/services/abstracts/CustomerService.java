package com.turkcell.customerservice.services.abstracts;

import com.turkcell.customerservice.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.customerservice.entities.dtos.auth.RegisterRequest;

public interface CustomerService {
  AuthenticationResponse register(RegisterRequest request);
}
