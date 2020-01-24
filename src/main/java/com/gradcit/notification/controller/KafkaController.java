package com.gradcit.notification.controller;

import com.gradcit.notification.messaging.Producer;
import com.gradcit.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

  private final Producer producer;

  @Autowired
  KafkaController(Producer producer) {
    this.producer = producer;
  }

  @PostMapping(value = "/notification")
  public void sendMessageToKafkaTopic2(@RequestBody Notification message) {
    this.producer.sendMessage(message);
  }
}
