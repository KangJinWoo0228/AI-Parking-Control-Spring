package com.gailab.parking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.dto.NormalUserDTO;
import com.gailab.parking.service.NormalUserService;
import com.gailab.parking.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class SocialController {
	@Autowired
	private NormalUserService normalUserService;
	
	
	
	@GetMapping("/api/normal_user/kakao")
	public Map<String, Object> getMemberFromKakao(String accessToken) {
		log.info("access Token: ");
		log.info(accessToken);
		
		NormalUserDTO normalUserDTO = normalUserService.getKakaoUser(accessToken);
		
		Map<String, Object> claims = normalUserDTO.getClaims();
		
		String jwtAccessToken = JWTUtil.generateToken(claims, 10);
		String jwtRefreshToken = JWTUtil.generateToken(claims, 60 * 24);
		
		claims.put("accessToken", jwtAccessToken);
		claims.put("refreshToken", jwtRefreshToken);
		
		System.out.println(normalUserDTO);
		
		return claims;
	}
	
	
}
