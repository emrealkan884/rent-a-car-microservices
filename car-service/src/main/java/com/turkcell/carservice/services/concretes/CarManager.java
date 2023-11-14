package com.turkcell.carservice.services.concretes;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.dtos.requests.CreateCarRequestDto;
import com.turkcell.carservice.entities.dtos.responses.CreatedCarResponseDto;
import com.turkcell.carservice.repositories.CarRepository;
import com.turkcell.carservice.services.abstracts.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;

    @Override
    public void add(CreateCarRequestDto request) {
        Car car = Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .colour(request.getColour())
                .modelYear(request.getModelYear())
                .dailyPrice(request.getDailyPrice())
                .build();
        carRepository.save(car);
    }

    @Override
    public List<Car> getAll() {
        return  carRepository.findAll();
    }
}
