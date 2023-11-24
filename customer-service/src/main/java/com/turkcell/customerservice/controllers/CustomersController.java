package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.entities.dtos.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.CustomerAddResponse;
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
  CustomerAddResponse register(@RequestBody CustomerAddRequest request) {
    return customerService.register(request);
  }
}
