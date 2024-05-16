package com.gailab.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.HouseholdService;
import com.gailab.parking.vo.AddressRequest;
import com.gailab.parking.vo.NormalUser;
import com.gailab.parking.vo.Vehicle;

@RestController
@RequestMapping("/api/household")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @PostMapping("/member/update")
    public int updateHouseholdMember(@RequestBody NormalUser member) {
        return householdService.updateHouseholdMember(member);
    }

    @PostMapping("/vehicles/update")
    public int updateHouseholdVehicle(@RequestBody Vehicle vehicle) {
        return householdService.updateHouseholdVehicle(vehicle);
    }
    
    @PostMapping("/approve")
    public ResponseEntity<String> approveRequest(@RequestBody AddressRequest addressRequest) {
        try {
            householdService.approveRequest(addressRequest);
            return ResponseEntity.ok("요청이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("요청을 처리하는 도중 오류가 발생했습니다.");
        }
    }
}