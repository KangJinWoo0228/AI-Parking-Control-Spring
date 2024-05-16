package com.gailab.parking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.dao.NormalUserRepository;
import com.gailab.parking.service.NormalUserService;
import com.gailab.parking.vo.NormalUser;

@RestController
public class NormalUserController {
	@Autowired
	NormalUserRepository normalUserRepository;
	
	@Autowired
	NormalUserService normalUserService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/test")
	public NormalUser getUser() {
		return normalUserRepository.getUser("user1@gailab.co.kr");
	}
	
	@PostMapping("/api/normal_user/register")
	public Map<String, String> register(NormalUser user) {
		user.setLoginPassword(passwordEncoder.encode(user.getLoginPassword()));
		System.out.println(user);
		normalUserRepository.register(user);
		return Map.of("result", "Successfully Registered");
	}
	
	@PutMapping("/api/normal_user/approve")
	public ResponseEntity<String> approveUser(@RequestBody Map<String, Object> request) {
		normalUserService.approveMasterUser(request);
		return ResponseEntity.ok("Successfully Approved User.");
	}
	
}
