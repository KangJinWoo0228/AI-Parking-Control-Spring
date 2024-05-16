package com.gailab.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.ReservationService;

import java.util.Map;

@RestController
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/api/reservation/register")
	public ResponseEntity<String> registerReservation(@RequestBody Map<String, Object> request) {
		reservationService.registerReservation(request);
		return ResponseEntity.ok("Successfully registered Reservation Information.");
	}
}
