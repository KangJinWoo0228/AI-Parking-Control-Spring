package com.gailab.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.SpecificRateService;
import com.gailab.parking.vo.SpecificRate;

@RestController
public class SpecificRateController {
	@Autowired
	SpecificRateService specificRateService;
	
	@GetMapping("/api/specific_rate/{addressId}")
	public SpecificRate getSpecificRate(@PathVariable Integer addressId) {
		return specificRateService.getSpecificRate(addressId);
	}
	
	@GetMapping("/api/specific_rate/all/{aptId}")
	public List<SpecificRate> getAllSpecificRate(@PathVariable("aptId") Integer aptId) {
		return specificRateService.getAllSpecificRate(aptId);
	}
}
