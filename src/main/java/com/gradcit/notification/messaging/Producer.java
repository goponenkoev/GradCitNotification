package com.gradcit.notification.messaging;

import com.gradcit.notification.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class Producer {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);
  private static final String TOPIC = "notifications";

  private final KafkaTemplate<String, Notification> kafkaTemplate2;

  public Producer(KafkaTemplate<String, Notification> kafkaTemplate2) {
    this.kafkaTemplate2 = kafkaTemplate2;
  }

  public void sendMessage(Notification notification) {
    logger.info("sending data='{}' to topic='{}'", notification, TOPIC);

    Message<Notification> message = MessageBuilder
        .withPayload(notification)
        .setHeader(KafkaHeaders.TOPIC, TOPIC)
        .build();

    kafkaTemplate2.send(message);
  }

}
