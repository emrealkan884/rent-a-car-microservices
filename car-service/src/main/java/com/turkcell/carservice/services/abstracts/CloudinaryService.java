package com.turkcell.carservice.services.abstracts;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
  String uploadFile(MultipartFile file, String folderName);
}
