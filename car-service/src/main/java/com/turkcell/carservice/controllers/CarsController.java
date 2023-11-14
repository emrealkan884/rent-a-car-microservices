package com.turkcell.carservice.controllers;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.dtos.requests.CreateCarRequestDto;
import com.turkcell.carservice.services.abstracts.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cars")
@RestController
@RequiredArgsConstructor
public class CarsController {
    private final CarService carService;

    @PostMapping
    public void add(@RequestBody CreateCarRequestDto request) {
        carService.add(request);
    }

    @GetMapping
    List<Car> getAll(){
        return carService.getAll();
    }
    @GetMapping("/deneme")
    public String deneme(){
        return "Deneme";
    }
}
