package com.turkcell.carservice.controllers;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.dtos.requests.CarAddRequest;
import com.turkcell.carservice.entities.dtos.requests.CarUpdateRequest;
import com.turkcell.carservice.entities.dtos.responses.CarAddResponse;
import com.turkcell.carservice.entities.dtos.responses.CarGetResponse;
import com.turkcell.carservice.entities.dtos.responses.CarUpdateResponse;
import com.turkcell.carservice.services.abstracts.CarService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cars")
@RestController
@RequiredArgsConstructor
public class CarsController {
  private final CarService carService;

  @PostMapping
  public CarAddResponse add(@RequestBody @Valid CarAddRequest request) {
    return carService.add(request);
  }

  @PutMapping("/inventoryCode")
  public CarUpdateResponse update(
      @RequestParam String inventoryCode, @RequestBody @Valid CarUpdateRequest request) {
    return carService.update(inventoryCode, request);
  }

  @DeleteMapping
  public void delete(String inventoryCode) {
    carService.delete(inventoryCode);
  }

  @GetMapping
  public List<CarGetResponse> getAll() {
    return carService.getAll();
  }

  @GetMapping("/getByInventoryCode")
  public Car getByInventoryCode(@RequestParam String inventoryCode) {
    return carService.getByInventoryCode(inventoryCode);
  }

  @GetMapping("/getStateByInventoryCode")
  public Boolean getStateByInventoryCode(@RequestParam String inventoryCode) {
    return carService.getStateByInventoryCode(inventoryCode);
  }

  @GetMapping("/getDailyPriceByInventoryCode")
  public Double getDailyPriceByInventoryCode(@RequestParam String inventoryCode) {
    return carService.getDailyPriceByInventoryCode(inventoryCode);
  }

  @PostMapping("/updateState")
  public Boolean updateState(@RequestParam String inventoryCode, @RequestParam Boolean state) {
    return carService.updateState(inventoryCode, state);
  }

  @GetMapping("/deneme")
  public String deneme() {
    return "Deneme";
  }
}
