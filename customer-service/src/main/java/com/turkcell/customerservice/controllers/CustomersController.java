package com.turkcell.customerservice.controllers;

import com.turkcell.customerservice.entities.dtos.CustomerAddRequest;
import com.turkcell.customerservice.entities.dtos.CustomerAddResponse;
import com.turkcell.customerservice.entities.dtos.CustomerUpdateRequest;
import com.turkcell.customerservice.entities.dtos.GetCustomerDto;
import com.turkcell.customerservice.services.abstracts.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/customers")
@RestController
@RequiredArgsConstructor
public class CustomersController {
  private final CustomerService customerService;

  @PostMapping
  public CustomerAddResponse register(@RequestBody CustomerAddRequest request) {
    return customerService.register(request);
  }

  @DeleteMapping
  public void delete(@RequestParam int id) {
    customerService.delete(id);
  }

  @PutMapping
  public GetCustomerDto update(@RequestParam int id, @RequestBody CustomerUpdateRequest request) {
    return customerService.update(id, request);
  }

  @GetMapping("/getById")
  public GetCustomerDto getById(int id) {
    return customerService.getById(id);
  }

  @GetMapping("/getAll")
  public List<GetCustomerDto> getAll() {
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
