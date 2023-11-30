package com.turkcell.carservice.services.abstracts;

import com.turkcell.carservice.entities.dtos.requests.ImageAddRequest;
import com.turkcell.carservice.entities.dtos.responses.ImageAddResponse;

public interface ImageService {
  ImageAddResponse uploadImage(ImageAddRequest imageAddRequest, String inventoryCode);
}
