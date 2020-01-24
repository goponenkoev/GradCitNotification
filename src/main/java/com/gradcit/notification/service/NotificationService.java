package com.gradcit.notification.service;

import com.gradcit.notification.utils.JWTUtils;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import java.sql.Timestamp;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private static final String FILE_NAME = "gradcit.p12";
  private static final String PASSWORD = "hbb38nJBoij84d2jknslapkasjw";

  //TODO: make all params as a POJO
  public void sendNotification(JSONObject requestedData, JSONObject body,
      String token, String recType, String imgId, Long userId, String username, Timestamp postDate, Integer badgeNum) {

    JSONObject aps = getAps(body);
    JSONObject request = getRequest(requestedData, aps, recType, imgId, userId, username, postDate, badgeNum);
    String payload = request.toString();

    ApnsService service = APNS.newService()
        .withCert(FILE_NAME, PASSWORD)
        .withSandboxDestination()
        .build();

    service.push(token, payload);

  }

  /*
   * -2 - лайк под комментом, ничего не показываем кроме коммента
   * -1 - подписался юзер, показываем его аватар
   * 0 - лайк под фото, показываем превью фото
   * 1 - лайк под текстовым постом
   * 0 - комментарий под записью без фото
   * 1 - комментарий под записью с фото
   * 2 - оценена запись без фото
   * 3 - оценена запись с фото
   * 4 - новый подписчик
   * 5 - оценен комментарий
   */
  private JSONObject getRequest(JSONObject requestedData, JSONObject aps, String recType, String imgId, Long userId,
      String username, Timestamp postDate, Integer badgeNum) {
    String jwtToken = JWTUtils.generateJWTToken(userId);

    JSONObject request = new JSONObject();
    request.put("aps", aps);
    request.put("requestedData", requestedData);
    request.put("recType", recType);
    request.put("imgId", imgId);
    request.put("pageDate", postDate);
    request.put("username", username);
    request.put("jwtToken", jwtToken);
    request.put("badgeNum", badgeNum);
    return request;
  }

  private JSONObject getAps(JSONObject body) {
    JSONObject aps = new JSONObject();
    aps.put("alert", body);
    aps.put("sound", "default");
    aps.put("badge", "1");
    aps.put("category", "CustomSamplePush");
    aps.put("mutable-content", "1");
    return aps;
  }

}

