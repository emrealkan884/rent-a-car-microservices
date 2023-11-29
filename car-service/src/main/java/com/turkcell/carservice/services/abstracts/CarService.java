package com.turkcell.carservice.services.abstracts;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.Image;
import com.turkcell.carservice.entities.dtos.requests.CarAddRequest;
import com.turkcell.carservice.entities.dtos.requests.CarUpdateRequest;
import com.turkcell.carservice.entities.dtos.responses.CarAddResponse;
import com.turkcell.carservice.entities.dtos.responses.CarGetResponse;
import com.turkcell.carservice.entities.dtos.responses.CarUpdateResponse;
import java.util.List;

public interface CarService {
  CarAddResponse add(CarAddRequest request);

  CarUpdateResponse update(String inventoryCode, CarUpdateRequest request);

  void delete(String inventoryCode);

  List<CarGetResponse> getAll();

  Car getByInventoryCode(String inventoryCode);

  Boolean getStateByInventoryCode(String inventoryCode);

  List<Image> getImagesByInventoryCode(String inventoryCode);

  Double getDailyPriceByInventoryCode(String inventoryCode);

  Boolean updateState(String inventoryCode, Boolean state);

  Car updateImageByInventoryCode(String inventoryCode, Image image);
}
