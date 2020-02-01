package com.gradcit.notification.messaging;

import com.gradcit.notification.model.Notification;
import com.gradcit.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class MessageHandler {

  private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

  private final NotificationService notificationService;

  public MessageHandler(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @KafkaListener(topics = "notifications")
  public void handleMessage(Notification notification) {
    logger.info("Consumed notification: {}", notification);
    notificationService.sendNotification(notification);
  }
}
