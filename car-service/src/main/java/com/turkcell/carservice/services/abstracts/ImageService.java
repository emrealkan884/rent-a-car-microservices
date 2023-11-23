package com.turkcell.carservice.services.abstracts;

import com.turkcell.carservice.entities.ImageModel;
import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface ImageService {
  ResponseEntity<Map> uploadImage(ImageModel imageModel, String inventoryCode);
}
