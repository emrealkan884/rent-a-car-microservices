package com.turkcell.carservice.services.concretes;

import com.cloudinary.Cloudinary;
import com.turkcell.carservice.services.abstracts.CloudinaryService;
import jakarta.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryManager implements CloudinaryService {

  @Resource private Cloudinary cloudinary;

  @Override
  public String uploadFile(MultipartFile file, String folderName) {
    try {
      HashMap<Object, Object> options = new HashMap<>();
      options.put("folder", folderName);
      Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
      String publicId = (String) uploadedFile.get("public_id");
      return cloudinary.url().secure(true).generate(publicId);

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}