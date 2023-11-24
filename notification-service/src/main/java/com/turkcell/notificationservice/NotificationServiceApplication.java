package com.turkcell.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class NotificationServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationServiceApplication.class, args);
  }

  @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
  public void handleNotificationEvent(String message) {
    System.out.println("Topicte bir mesaj yakalandı: " + message);
  }
}
