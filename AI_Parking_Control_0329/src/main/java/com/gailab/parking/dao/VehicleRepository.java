package com.gailab.parking.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.Vehicle;

@Mapper
public interface VehicleRepository {
	Integer existVehicleNumber(String vehicleNumber);
	void insertVehicleInfo(Map<String, Object> request);
	void insertMyAddressVehicleInfo(Map<String, Object> request);
	
	public List<Vehicle> getAllVehicles(Long aptId);
	
	public List<Vehicle> getHouseholdVehicles(String addressId);
	
	int registVehicle(Vehicle vehicle);
}
