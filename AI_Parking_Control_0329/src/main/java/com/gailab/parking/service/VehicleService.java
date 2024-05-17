package com.gailab.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.VehicleRepository;
import com.gailab.parking.vo.Vehicle;

import java.util.List;
import java.util.Map;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public void insertMyAddressVehicleInfo(Map<String, Object> request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> info = (Map<String, Object>) request.get("info");
		System.out.println("info" + info);
		vehicleRepository.insertMyAddressVehicleInfo(info);
	}
	
	public List<Vehicle> getAllVehicles(Long aptId) {
		return vehicleRepository.getAllVehicles(aptId);
	}
	
	public List<Vehicle> getHouseholdVehicles(String addressId) {
		return vehicleRepository.getHouseholdVehicles(addressId);
	}
	
	public int registVehicle(Vehicle vehicle) {
		return vehicleRepository.registVehicle(vehicle);
	}
}
