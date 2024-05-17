package com.gailab.parking.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.util.CustomJWTException;
import com.gailab.parking.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {
	@RequestMapping("/api/normal_user/refresh")
	public Map<String, Object> refresh(@RequestHeader("Authorization")String authHeader, String refreshUserToken) {
	    if (refreshUserToken == null) {
	        throw new CustomJWTException("NULL_REFRESH");
	    }
	    if (authHeader == null || authHeader.length() < 7) {
	        throw new CustomJWTException("INVALID_STRING");
	    }

	    String accessUserToken = authHeader.substring(7);

	    if (!checkExpiredToken(accessUserToken, "userKey")) {
	        return Map.of("accessUserToken", accessUserToken, "refreshToken", refreshUserToken);
	    }

	    Map<String, Object> claims = JWTUtil.validateToken(refreshUserToken);

	    log.info("refresh... claims: " + claims);

	    String newAccessToken = JWTUtil.generateToken(claims, 60 * 24);
	    String newRefreshToken = checkTime((Integer)claims.get("exp")) == true ? JWTUtil.generateToken(claims, 60 * 24) : refreshUserToken;

	    return Map.of("accessUserToken", newAccessToken, "refreshUserToken", newRefreshToken);
	} 

	@RequestMapping("/api/manager/refresh")
	public Map<String, Object> refreshManager(@RequestHeader("Authorization")String authHeader, String refreshManagerToken) {
	    if (refreshManagerToken == null) {
	        throw new CustomJWTException("NULL_REFRESH");
	    }
	    if (authHeader == null || authHeader.length() < 7) {
	        throw new CustomJWTException("INVALID_STRING");
	    }

	    String accessManagerToken = authHeader.substring(7);

	    if (!checkExpiredToken(accessManagerToken, "managerKey")) {
	        return Map.of("accessManagerToken", accessManagerToken, "refreshToken", refreshManagerToken);
	    }

	    Map<String, Object> claims = JWTUtil.validateToken(refreshManagerToken);

	    log.info("refresh... claims: " + claims);

	    String newAccessToken = JWTUtil.generateToken(claims, 60 * 24);
	    String newRefreshToken = checkTime((Integer)claims.get("exp")) == true ? JWTUtil.generateToken(claims, 60 * 24) : refreshManagerToken;

	    return Map.of("accessManagerToken", newAccessToken, "refreshManagerToken", newRefreshToken);
	}

	
	private boolean checkTime(Integer exp) {
		java.util.Date expDate = new java.util.Date((long)exp * (1000));
		long gap = expDate.getTime() - System.currentTimeMillis();
		long leftMin = gap / (1000 * 60);
		return leftMin < 60;
	}
	
	private boolean checkExpiredToken(String token, String keyType) {
	    try {
	        JWTUtil.validateToken(token);
	    } catch (CustomJWTException ex) {
	        if (ex.getMessage().equals("Expired")) {
	            return true;
	        }
	    }
	    return false;
	}

}
