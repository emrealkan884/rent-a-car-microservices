package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.customerservice.entities.dtos.auth.RegisterRequest;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customers")
@RestController
@RequiredArgsConstructor
public class CustomersController {
  private final CustomerService customerService;

  @PostMapping
  AuthenticationResponse register(@RequestBody RegisterRequest request) {
    return customerService.register(request);
  }
}
