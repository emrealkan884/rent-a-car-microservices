package com.turkcell.carservice.services.abstracts;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.dtos.requests.CreateCarRequestDto;
import com.turkcell.carservice.entities.dtos.responses.CreatedCarResponseDto;

import java.util.List;

public interface CarService {
    void add(CreateCarRequestDto request);
    List<Car> getAll();
}
