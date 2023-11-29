package com.turkcell.carservice.core.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

  private final String cloudName = "dfxjoh1ce";
  private final String apiKey = "125727768972878";
  private final String apiSecret = "N6pEDNw1exVqHlfUtmHGG9w219s";

  @Bean
  public Cloudinary cloudinary() {
    return new Cloudinary(
        ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret));
  }
}
