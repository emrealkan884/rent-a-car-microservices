package com.turkcell.carservice.services.concretes;

import com.turkcell.carservice.core.exceptions.BusinessException;
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
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

  private final CarRepository carRepository;
  private final ModelMapper modelMapper;
  private final MessageSource messageSource;

  @Override
  public CarAddResponse add(CarAddRequest request) {
    checkIfCarExist(request.getInventoryCode());
    Car carFromAutoMapping = modelMapper.map(request, Car.class);
    carFromAutoMapping = carRepository.save(carFromAutoMapping);

    // Ekleme islemimizin sonucunu göstermek için responce
    CarAddResponse carAddResponse = modelMapper.map(carFromAutoMapping, CarAddResponse.class);
    return carAddResponse;
  }

  @Override
  public CarUpdateResponse update(String inventoryCode, CarUpdateRequest request) {
    Car car = carRepository.findByInventoryCode(inventoryCode);
    car.setDailyPrice(request.getDailyPrice());
    car.setImages(new ArrayList<>());
    car.setState(request.getState());
    car = carRepository.save(car);

    CarUpdateResponse carUpdateResponse = modelMapper.map(car, CarUpdateResponse.class);
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
    List<CarGetResponse> carGetResponses =
        cars.stream().map(item -> modelMapper.map(item, CarGetResponse.class)).toList();
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

  private void checkIfCarExist(String inventoryCode) {
    Car car = getByInventoryCode(inventoryCode);
    if (car != null) {
      throw new BusinessException(
          messageSource.getMessage(
              "checkIfCarExist", new Object[] {inventoryCode}, LocaleContextHolder.getLocale()));
    }
  }
}
