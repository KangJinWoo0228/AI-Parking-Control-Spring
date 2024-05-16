package com.gailab.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.AptService;
import com.gailab.parking.vo.Apt;
import com.gailab.parking.vo.AptAddress;
import com.gailab.parking.vo.NormalUser;
import com.gailab.parking.vo.Request;

import java.util.*;

@RestController
public class AptController {
	@Autowired
	private AptService aptService;
	
	@GetMapping("/api/apt")
	public List<Apt> getAllApts() {
		return aptService.getAllApts();
	}
	
	@GetMapping("/api/apt/dong/{aptId}")
	public List<String> getAptDong(@PathVariable("aptId") String aptId) {
		return aptService.getAptDong(aptId);
	}
	
	@GetMapping("/api/apt/ho/{dong}")
	public List<String> getAptHo(@PathVariable("dong") String dong) {
		return aptService.getAptHo(dong);
	}
	
	@GetMapping("/api/address/{aptId}")
	public List<AptAddress> getAllAddress(@PathVariable("aptId") String aptId) {
		return aptService.getAptAddress(aptId);
	}
	
	@PostMapping("/api/apt/addressRequest")
	public ResponseEntity<String> addressRequest(@RequestBody Map<String, Object> request) {
		aptService.addressRequest(request);
		return ResponseEntity.ok("Data processed successfully!");
	}
	
	@GetMapping("/api/apt/address/{userId}")
	public List<String> getUserAddresses(@PathVariable("userId") int userId) {
		System.out.println("userId : " + userId);
		return aptService.getUserAddresses(userId);
	}
	
	@GetMapping("/api/request/{addressId}")
	public List<Request> getRequest(@PathVariable ("addressId") String addressId) {
		return aptService.getRequest(addressId);
	}
	
	@GetMapping("/api/request/household/{addressId}")
	public List<NormalUser> getHouseholdMember(@PathVariable ("addressId") String addressId) {
		return aptService.getHouseholdMember(addressId);
	}
}
