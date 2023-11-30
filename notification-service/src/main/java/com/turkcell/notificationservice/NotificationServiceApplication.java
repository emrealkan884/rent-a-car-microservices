package com.turkcell.notificationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@RequiredArgsConstructor
public class NotificationServiceApplication {
  private final MessageSource messageSource;

  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }

  @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
  public void handleNotificationEvent(String message) {

    System.out.println(
        messageSource.getMessage("ThereIsAMessage", null, LocaleContextHolder.getLocale())
            + message);
  }
}
