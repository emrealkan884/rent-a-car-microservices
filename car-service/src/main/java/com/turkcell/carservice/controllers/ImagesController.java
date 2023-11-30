package com.turkcell.carservice.controllers;

import com.turkcell.carservice.entities.dtos.requests.ImageAddRequest;
import com.turkcell.carservice.entities.dtos.responses.ImageAddResponse;
import com.turkcell.carservice.repositories.ImageRepository;
import com.turkcell.carservice.services.abstracts.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/images")
@RestController
@RequiredArgsConstructor
public class ImagesController {

  @Autowired private ImageRepository imageRepository;

  @Autowired private ImageService imageService;

  @PostMapping("/upload")
  public ImageAddResponse upload(
      ImageAddRequest imageAddRequest, @RequestParam String inventoryCode) {
    try {
      return imageService.uploadImage(imageAddRequest, inventoryCode);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
