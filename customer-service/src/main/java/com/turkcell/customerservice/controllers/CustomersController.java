package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.entities.dtos.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.CustomerAddResponse;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customers")
@RestController
@RequiredArgsConstructor
public class CustomersController {
  private final CustomerService customerService;

  @PostMapping
  CustomerAddResponse register(@RequestBody CustomerAddRequest request) {
    return customerService.register(request);
  }

  @PostMapping("/balanceUp")
  double balanceUp(@RequestParam int customerId, @RequestParam double balance) {
    return customerService.balanceUp(customerId, balance);
  }

  @PostMapping("/balanceDown")
  double balanceDown(@RequestParam int customerId, @RequestParam double balance) {
    return customerService.balanceDown(customerId, balance);
  }

  @GetMapping("/getBalanceByCustomerId")
  double getBalanceByCustomerId(@RequestParam int customerId) {
    return customerService.getBalanceByCustomerId(customerId);
  }
}
