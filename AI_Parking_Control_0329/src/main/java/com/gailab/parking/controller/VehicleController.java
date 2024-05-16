package com.gailab.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.VehicleService;
import com.gailab.parking.vo.Vehicle;

import java.util.List;
import java.util.Map;

@RestController
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/api/vehicle/insert_my_address")
	public ResponseEntity<String> insertMyAddressVehicleInfo(@RequestBody Map<String, Object> request) {
		vehicleService.insertMyAddressVehicleInfo(request);
		System.out.println("request" + request);
		return ResponseEntity.ok("Successfully inserted My Address Vehicle");
	}
	
	@GetMapping("/api/vehicle/all/{aptId}")
	public List<Vehicle> getAllVehicles(@PathVariable("aptId") Long aptId) {
		return vehicleService.getAllVehicles(aptId);
	}
	
	@GetMapping("/api/vehicle/household/{addressId}")
	public List<Vehicle> getHouseholdVehicles(@PathVariable("addressId") String addressId) {
		return vehicleService.getHouseholdVehicles(addressId);
	}
}
