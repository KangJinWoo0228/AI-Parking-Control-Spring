package com.gailab.parking.config.handler;

import java.io.IOException;
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Manager login success....");
        // Manager 계정 로그인 성공 시 JWT 토큰 생성 및 반환
        ManagerDTO managerDTO = (ManagerDTO) authentication.getPrincipal();
        Map<String, Object> claims = managerDTO.getClaims();

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
