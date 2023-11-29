package com.turkcell.carservice.services.concretes;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.Image;
import com.turkcell.carservice.entities.dtos.requests.CarAddRequest;
import com.turkcell.carservice.entities.dtos.requests.CarUpdateRequest;
import com.turkcell.carservice.entities.dtos.responses.CarAddResponse;
import com.turkcell.carservice.entities.dtos.responses.CarGetResponse;
import com.turkcell.carservice.entities.dtos.responses.CarUpdateResponse;
import com.turkcell.carservice.repositories.CarRepository;
import com.turkcell.carservice.services.abstracts.CarService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

  private final CarRepository carRepository;

  @Override
  public CarAddResponse add(CarAddRequest request) {
    Car car =
        Car.builder()
            .inventoryCode(request.getInventoryCode())
            .brand(request.getBrand())
            .model(request.getModel())
            .colour(request.getColour())
            .modelYear(request.getModelYear())
            .dailyPrice(request.getDailyPrice())
            .state(request.getState())
            .build();
    car = carRepository.save(car);
    CarAddResponse carAddResponse =
        CarAddResponse.builder()
            .state(car.getState())
            .modelYear(car.getModelYear())
            .brand(car.getBrand())
            .colour(car.getColour())
            .inventoryCode(car.getInventoryCode())
            .dailyPrice(car.getDailyPrice())
            .model(car.getModel())
            .build();
    return carAddResponse;
  }

  @Override
  public CarUpdateResponse update(String inventoryCode, CarUpdateRequest request) {
    Car car = carRepository.findByInventoryCode(inventoryCode);
    car.setDailyPrice(request.getDailyPrice());
    car.setImages(new ArrayList<>());
    car.setState(request.getState());
    car = carRepository.save(car);
    CarUpdateResponse carUpdateResponse =
        CarUpdateResponse.builder()
            .state(car.getState())
            .modelYear(car.getModelYear())
            .brand(car.getBrand())
            .colour(car.getColour())
            .inventoryCode(car.getInventoryCode())
            .dailyPrice(car.getDailyPrice())
            .model(car.getModel())
            .build();
    return carUpdateResponse;
  }

  @Override
  public void delete(String inventoryCode) {
    Car car = carRepository.findByInventoryCode(inventoryCode);
    carRepository.delete(car);
  }

  @Override
  public List<CarGetResponse> getAll() {
    List<Car> cars = carRepository.findAll();
    List<CarGetResponse> carGetResponses = new ArrayList<>();
    CarGetResponse carGetResponse = new CarGetResponse();
    for (Car car : cars) {
      carGetResponse.setModel(car.getModel());
      carGetResponse.setModelYear(car.getModelYear());
      carGetResponse.setBrand(car.getBrand());
      carGetResponse.setDailyPrice(car.getDailyPrice());
      carGetResponse.setColour(car.getColour());
      carGetResponses.add(carGetResponse);
    }
    return carGetResponses;
  }

  @Override
  public Car getByInventoryCode(String inventoryCode) {
    return carRepository.findByInventoryCode(inventoryCode);
  }

  @Override
  public Boolean getStateByInventoryCode(String inventoryCode) {
    Car car = getByInventoryCode(inventoryCode);
    if (car != null) {
      return car.getState();
    }
    return false;
  }

  public List<Image> getImagesByInventoryCode(String inventoryCode) {
    Car car = getByInventoryCode(inventoryCode);
    return car.getImages();
  }

  @Override
  public Double getDailyPriceByInventoryCode(String inventoryCode) {
    Car car = getByInventoryCode(inventoryCode);
    return car.getDailyPrice();
  }

  @Override
  public Boolean updateState(String inventoryCode, Boolean state) {
    Car car = getByInventoryCode(inventoryCode);
    car.setState(state);
    car = carRepository.save(car);
    return car.getState();
  }

  @Override
  public Car updateImageByInventoryCode(String inventoryCode, Image image) {
    Car car = getByInventoryCode(inventoryCode);
    List<Image> images = car.getImages();
    images.add(image);
    return carRepository.save(car);
  }
}
