package com.gailab.parking.controller;

import java.util.List;import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.ParkingRuleService;
import com.gailab.parking.vo.ParkingRule;
import com.gailab.parking.vo.ParkingRuleVacation;

@RestController
public class ParkingRuleController {
	@Autowired
	private ParkingRuleService parkingRuleService;
	
	@GetMapping("/api/parking/rule/{aptId}")
	public List<ParkingRule> getParkingRule(@PathVariable("aptId") Long aptId) {
		return parkingRuleService.getParkingRule(aptId);
	}
	
	@GetMapping("/api/parking/rule/vacation/{aptId}")
	public List<ParkingRuleVacation> getVacation(@PathVariable("aptId") Long aptId) {
		return parkingRuleService.getParkingRuleVacation(aptId);
	}
	
    @PutMapping("/api/parking/rule/{parkingRuleId}")
    public ResponseEntity<String> updateParkingRule(@PathVariable("parkingRuleId") Integer parkingRuleId, @RequestBody ParkingRule updatedRule) {
		boolean updated = parkingRuleService.updateParkingRule(parkingRuleId, updatedRule);
		if (updated) {
		return ResponseEntity.ok("Parking rule updated successfully");
		} else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking rule not found");
		}
    }
    
//    @PutMapping("/api/parking/rule/update/vacation/{aptId}")
//    public ResponseEntity<String> updateParkingRuleVacation(@PathVariable("aptId") Integer aptId, @RequestBody ParkingRuleVacation updatedVacation) {
//    	boolean updated = parkingRuleService.updateParkingRuleVacation(aptId, updatedVacation);
//    }
//    
//    @PutMapping("/api/parking/rule/remove/vacation/{aptId}")
//    public ResponseEntity<String> removeParkingRuleVacation(@PathVariable("aptId") Integer aptId, @RequestBody ParkingRuleVacation removedVacation) {
//    	boolean updated = parkingRuleService.removeParkingRuleVacation(aptId, removedVacation);
//    }
    
}
