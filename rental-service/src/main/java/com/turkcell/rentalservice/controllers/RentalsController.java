package com.turkcell.rentalservice.controllers;

import com.turkcell.rentalservice.services.abstracts.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/rentals")
@RestController
@RequiredArgsConstructor
public class RentalsController {
  private final RentalService rentalService;

  @GetMapping("/submitRental")
  public String submitRental(@RequestParam String inventoryCode, @RequestParam int customerId) {
    return rentalService.submitRental(inventoryCode, customerId);
  }
}
