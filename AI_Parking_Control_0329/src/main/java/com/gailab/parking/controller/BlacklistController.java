package com.gailab.parking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.BlacklistService;
import com.gailab.parking.vo.Blacklist;

@RestController
public class BlacklistController {
	@Autowired
	BlacklistService blacklistService;
	
	@PostMapping("/api/blacklist/report")
	public ResponseEntity<String> reportBlacklist(@RequestBody Map<String, Object> request) {
		blacklistService.reportBlacklist(request);
		return ResponseEntity.ok("Successfully inserted My Address Vehicle");
	}
	
	@GetMapping("/api/blacklist/{aptId}")
	public List<Blacklist> getAllBlacklist(@PathVariable("aptId") String aptId) {
		return blacklistService.getAllBlacklist(aptId);
	}
	
}