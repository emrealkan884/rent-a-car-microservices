package com.turkcell.carservice.repositories;

import com.turkcell.carservice.entities.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarRepository extends MongoRepository<Car,String> {
    Car findByInventoryCode(String inventoryCode);


}
