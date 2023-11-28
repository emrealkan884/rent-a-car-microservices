package com.turkcell.rentalservice.controllers;

import com.turkcell.rentalservice.entities.dtos.requests.RentalUpdateRequest;
import com.turkcell.rentalservice.entities.dtos.responses.RentalGetResponse;
import com.turkcell.rentalservice.entities.dtos.responses.RentalUpdateResponse;
import com.turkcell.rentalservice.services.abstracts.RentalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/rentals")
@RestController
@RequiredArgsConstructor
public class RentalsController {
  private final RentalService rentalService;

  @GetMapping("/submitRental")
  public String submitRental(@RequestParam String inventoryCode, @RequestParam int customerId) {
    return rentalService.submitRental(inventoryCode, customerId);
  }

  @DeleteMapping
  public void delete(int id) {
    rentalService.delete(id);
  }

  @PutMapping
  public RentalUpdateResponse update(
      @RequestParam int id, @RequestBody RentalUpdateRequest request) {
    return rentalService.update(id, request);
  }

  @GetMapping("/getById")
  public RentalGetResponse getById(int id) {
    return rentalService.getById(id);
  }

  @GetMapping("/getAll")
  public List<RentalGetResponse> getAll() {
    return rentalService.getAll();
  }
}
