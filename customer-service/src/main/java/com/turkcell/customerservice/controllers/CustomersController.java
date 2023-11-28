package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.entities.dtos.requests.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.requests.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.responses.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerGetResponse;
import com.turkcell.customerservice.entities.dtos.responses.CustomerUpdateResponse;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customers")
@RestController
@RequiredArgsConstructor
public class CustomersController {
  private final CustomerService customerService;

  @PostMapping
  public CustomerAddResponse register(@RequestBody @Valid CustomerAddRequest request) {
    return customerService.register(request);
  }

  @DeleteMapping
  public void delete(@RequestParam int id) {
    customerService.delete(id);
  }

  @PutMapping
  public CustomerUpdateResponse update(
      @RequestParam int id, @RequestBody @Valid CustomerUpdateRequest request) {
    return customerService.update(id, request);
  }

  @GetMapping("/getById")
  public CustomerGetResponse getById(int id) {
    return customerService.getById(id);
  }

  @GetMapping("/getAll")
  public List<CustomerGetResponse> getAll() {
    return customerService.getAll();
  }

  @PostMapping("/balanceUp")
  public double balanceUp(@RequestParam int customerId, @RequestParam double balance) {
    return customerService.balanceUp(customerId, balance);
  }

  @PostMapping("/balanceDown")
  public double balanceDown(@RequestParam int customerId, @RequestParam double balance) {
    return customerService.balanceDown(customerId, balance);
  }

  @GetMapping("/getBalanceByCustomerId")
  public double getBalanceByCustomerId(@RequestParam int customerId) {
    return customerService.getBalanceByCustomerId(customerId);
  }
}
