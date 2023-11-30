package com.turkcell.carservice.entities.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageAddRequest {
  private String name;
  private MultipartFile file;
}
