package com.turkcell.carservice.services.abstracts;

import com.turkcell.carservice.entities.ImageModel;
import org.springframework.http.ResponseEntity;

public interface ImageService {
  ResponseEntity<String> uploadImage(ImageModel imageModel, String inventoryCode);
}
