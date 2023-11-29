package com.turkcell.carservice.services.concretes;

import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.Image;
import com.turkcell.carservice.entities.ImageModel;
import com.turkcell.carservice.repositories.ImageRepository;
import com.turkcell.carservice.services.abstracts.CarService;
import com.turkcell.carservice.services.abstracts.CloudinaryService;
import com.turkcell.carservice.services.abstracts.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageManager implements ImageService {

  private final CloudinaryService cloudinaryService;
  private final ImageRepository imageRepository;
  private final CarService carService;

  @Override
  public ResponseEntity<String> uploadImage(ImageModel imageModel, String inventoryCode) {
    try {
      if (imageModel.getName().isEmpty()) {
        return ResponseEntity.badRequest().build();
      }
      if (imageModel.getFile().isEmpty()) {
        return ResponseEntity.badRequest().build();
      }
      Image image = new Image();
      image.setName(imageModel.getName());
      image.setUrl(cloudinaryService.uploadFile(imageModel.getFile(), "folder_1"));
      if (image.getUrl() == null) {
        return ResponseEntity.badRequest().build();
      }
      Car car = carService.updateImageByInventoryCode(inventoryCode, image);
      image.setCar(car);
      imageRepository.save(image);
      return new ResponseEntity<>(image.getUrl(), HttpStatusCode.valueOf(200));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
