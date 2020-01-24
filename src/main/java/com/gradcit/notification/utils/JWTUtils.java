package com.gradcit.notification.utils;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import com.auth0.jwt.JWT;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JWTUtils {

  private static final String SECRET = "SecretKeyToGenJWTs";
  private static final long EXPIRATION_TIME = 864_000_000; // 10 days
  private static final String TOKEN_PREFIX = "Bearer ";

  public static String generateJWTToken(Long userId) {
    return TOKEN_PREFIX + JWT.create()
        .withSubject(String.valueOf(userId))
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));
  }
}
