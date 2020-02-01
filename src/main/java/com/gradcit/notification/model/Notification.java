package com.gradcit.notification.model;

import java.sql.Timestamp;
import lombok.Data;
import org.json.JSONObject;

@Data
public class Notification {

  private Long userId;
  private String imgId;
  private String token;
  private String recType;
  private JSONObject body;
  private String username;
  private Integer badgeNum;
  private Timestamp postDate;
  private JSONObject requestedData;

}