package com.gailab.parking.config.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gailab.parking.dto.NormalUserDTO;
import com.gailab.parking.util.JWTUtil;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("----------");
		log.info(authentication);
		log.info("----------");
		
		NormalUserDTO normalUserDTO = (NormalUserDTO) authentication.getPrincipal();
		Map<String, Object> claims = normalUserDTO.getClaims();
		
		String accessUserToken = JWTUtil.generateToken(claims, 10);
		String refreshUserToken = JWTUtil.generateToken(claims, 60 * 24);
		
		claims.put("accessUserToken", accessUserToken);
		claims.put("refreshUserToken", refreshUserToken);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(claims);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);
		printWriter.close();
	}
}
