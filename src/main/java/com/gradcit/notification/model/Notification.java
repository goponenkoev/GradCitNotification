package com.gradcit.notification.model;

import java.security.Timestamp;
import lombok.Data;
import org.json.JSONObject;

@Data
public class Notification {

  private JSONObject requestedData;
  private JSONObject body;
  private String token;
  private String recType;
  private String imgId;
  private Long userId;
  private String username;
  private Timestamp postDate;
  private Integer badgeNum;

}