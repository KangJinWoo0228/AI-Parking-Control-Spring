package com.gailab.parking.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.NormalUser;
import com.gailab.parking.vo.Vehicle;

@Mapper
public interface HouseholdRepository {
    int updateHouseholdMember(NormalUser member);
    int updateHouseholdVehicle(Vehicle vehicle);
    void approveRequest(Long userId);
    void rejectRequest(Long userId);
}
