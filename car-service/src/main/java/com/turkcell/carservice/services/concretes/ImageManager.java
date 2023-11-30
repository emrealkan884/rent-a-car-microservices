package com.turkcell.carservice.services.concretes;

import com.turkcell.carservice.core.exceptions.BusinessException;
import com.turkcell.carservice.entities.Car;
import com.turkcell.carservice.entities.Image;
import com.turkcell.carservice.entities.dtos.requests.ImageAddRequest;
import com.turkcell.carservice.entities.dtos.responses.ImageAddResponse;
import com.turkcell.carservice.repositories.ImageRepository;
import com.turkcell.carservice.services.abstracts.CarService;
import com.turkcell.carservice.services.abstracts.CloudinaryService;
import com.turkcell.carservice.services.abstracts.ImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageManager implements ImageService {

  private final CloudinaryService cloudinaryService;
  private final ImageRepository imageRepository;
  private final MessageSource messageSource;
  private final ModelMapper modelMapper;
  private final CarService carService;

  @Override
  public ImageAddResponse uploadImage(ImageAddRequest imageAddRequest, String inventoryCode) {
    checkIfImageNameEmpty(imageAddRequest);
    checkIfFileEmpty(imageAddRequest);
    Image image = new Image();
    image.setName(imageAddRequest.getName());
    image.setUrl(cloudinaryService.uploadFile(imageAddRequest.getFile(), "folder_1"));
    checkIfImageUrlNull(image);
    Car car = carService.updateImageByInventoryCode(inventoryCode, image);
    image.setCar(car);
    image = imageRepository.save(image);
    ImageAddResponse imageAddResponse = modelMapper.map(image, ImageAddResponse.class);
    return imageAddResponse;
  }

  private void checkIfImageNameEmpty(ImageAddRequest imageAddRequest) {
    if (imageAddRequest.getName().isEmpty()) {
      throw new BusinessException(
          messageSource.getMessage("checkIfImageNameEmpty", null, LocaleContextHolder.getLocale()));
    }
  }

  private void checkIfFileEmpty(ImageAddRequest imageAddRequest) {
    if (imageAddRequest.getFile().isEmpty()) {
      throw new BusinessException(
          messageSource.getMessage("checkIfFileEmpty", null, LocaleContextHolder.getLocale()));
    }
  }

  private void checkIfImageUrlNull(Image image) {
    if (image.getUrl() == null) {
      throw new BusinessException(
          messageSource.getMessage("checkIfImageUrlNull", null, LocaleContextHolder.getLocale()));
    }
  }
}
