package com.gailab.parking.util;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

public class JWTUtil {
    private static String userKey = "userKey";
    private static String managerKey = "managerKey";

    public static String generateUserToken(Map<String, Object> valueMap, int min) {
        return generateToken(valueMap, min, userKey);
    }

    public static String generateManagerToken(Map<String, Object> valueMap, int min) {
        return generateToken(valueMap, min, managerKey);
    }

    private static String generateToken(Map<String, Object> valueMap, int min, String keyType) {
        String key;
        if (keyType.equals(userKey)) {
            key = "userKeySecret";
        } else if (keyType.equals(managerKey)) {
            key = "managerKeySecret";
        } else {
            throw new IllegalArgumentException("Invalid key type");
        }

        SecretKey secretKey;
        try {
            secretKey = Keys.hmacShaKeyFor(key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(secretKey)
                .compact();
    }

    public static Map<String, Object> validateToken(String token, String keyType) {
        String key;
        if (keyType.equals(userKey)) {
            key = "userKeySecret";
        } else if (keyType.equals(managerKey)) {
            key = "managerKeySecret";
        } else {
            throw new IllegalArgumentException("Invalid key type");
        }

        SecretKey secretKey;
        try {
            secretKey = Keys.hmacShaKeyFor(key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }

        Map<String, Object> claim = null;
        try {
            claim = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException malformedJwtException) {
            throw new CustomJWTException("MalFormed");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException invalidClaimException) {
            throw new CustomJWTException("Invalid");
        } catch (JwtException jwtException) {
            throw new CustomJWTException("JWTError");
        } catch (Exception e) {
            throw new CustomJWTException("Error");
        }
        return claim;
    }
}

