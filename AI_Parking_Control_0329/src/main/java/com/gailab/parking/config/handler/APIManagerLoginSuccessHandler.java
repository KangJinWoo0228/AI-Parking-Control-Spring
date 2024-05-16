package com.gailab.parking.config.handler;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gailab.parking.dto.ManagerDTO;
import com.gailab.parking.util.JWTUtil;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class APIManagerLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws java.io.IOException, ServletException {
		log.info("----------");
		log.info(authentication);
		log.info("----------");
		
		ManagerDTO managerDTO = (ManagerDTO) authentication.getPrincipal();
		Map<String, Object> claims = managerDTO.getClaims();
		
		String accessManagerToken = JWTUtil.generateManagerToken(claims, 10);
		String refreshManagerToken = JWTUtil.generateManagerToken(claims, 60*24);
		
		claims.put("accessManagerToken", accessManagerToken);
		claims.put("refreshManagerToken", refreshManagerToken);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(claims);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);
		printWriter.close();
	}
}
