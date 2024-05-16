package com.gailab.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.HouseholdRepository;
import com.gailab.parking.vo.AddressRequest;
import com.gailab.parking.vo.NormalUser;
import com.gailab.parking.vo.Vehicle;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public int updateHouseholdMember(NormalUser member) {
        return householdRepository.updateHouseholdMember(member);
    }

    public int updateHouseholdVehicle(Vehicle vehicle) {
        return householdRepository.updateHouseholdVehicle(vehicle);
    }
    
    // address_request 승인/거절
    public void approveRequest(AddressRequest addressRequest) {
        if (addressRequest.getDelFlag() == 1) {
            approve(addressRequest.getUserId());
        } else if (addressRequest.getDelFlag() == 2) {
            reject(addressRequest.getUserId());
        }
    }

    private void approve(Long userId) {
        householdRepository.approveRequest(userId);
    }

    private void reject(Long userId) {
        householdRepository.rejectRequest(userId);
    }

}
