package com.gradcit.notification.messaging;

import com.gradcit.notification.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {

  private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

  @KafkaListener(topics = "notifications")
  public void handleMessage(Notification message) {
    logger.info("Consumed message: {}", message);
  }
}
