package com.turkcell.carservice.services.abstracts;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.Image;
import com.turkcell.carservice.entities.dtos.requests.CreateCarRequestDto;
import java.util.List;

public interface CarService {
  void add(CreateCarRequestDto request);

  void update(String inventoryCode, CreateCarRequestDto request);

  void delete(String inventoryCode);

  List<Car> getAll();

  Car getByInventoryCode(String inventoryCode);

  Boolean getStateByInventoryCode(String inventoryCode);

  List<Image> getImagesByInventoryCode(String inventoryCode);

  Double getDailyPriceByInventoryCode(String inventoryCode);

  Boolean updateState(String inventoryCode, Boolean state);
}
