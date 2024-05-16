package com.gailab.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.InoutService;
import com.gailab.parking.vo.InoutHistory;

import java.util.List;
import java.util.Map;

@RestController
public class InoutController {
	@Autowired
	private InoutService inoutService;
	
	@GetMapping("/api/inout/list/{addressId}")
	public List<Map<String, Object>> getAddressInoutHistory(@PathVariable("addressId") Long addressId) {
		return inoutService.getAddressInoutHistory(addressId);
	}
	
	@GetMapping("/api/inout/history/{aptId}")
	public List<InoutHistory> getAllInoutHistories(@PathVariable("aptId") String aptId){
		return inoutService.getAllInoutHistories(aptId);
	}
}
