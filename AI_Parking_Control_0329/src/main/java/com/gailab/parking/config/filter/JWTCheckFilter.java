package com.gailab.parking.config.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.gailab.parking.util.JWTUtil;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    private static final String USER_KEY = "yourUserKey";
    private static final String MANAGER_KEY = "yourManagerKey";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURI();
        log.info("check uri......" + path);

        if (path.startsWith("/api") || (path.startsWith("/manager"))) {
        	return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("---------");
        String authHeaderStr = request.getHeader("Authorization");
        if (authHeaderStr == null || !authHeaderStr.startsWith("Bearer")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }
        try {
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims;
            if (request.getRequestURI().startsWith("/api")) {
                claims = JWTUtil.validateToken(accessToken);
            } else {
                claims = JWTUtil.validateToken(accessToken);
            }
            log.info("JWT claims: " + claims);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("JWT Check Error.....");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }
    }

}

