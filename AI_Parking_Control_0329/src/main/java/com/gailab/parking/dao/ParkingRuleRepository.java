package com.gailab.parking.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gailab.parking.vo.ParkingRule;
import com.gailab.parking.vo.ParkingRuleVacation;

@Mapper
public interface ParkingRuleRepository {
	
	public List<ParkingRule> getParkingRule(Long aptId);
	
	public List<ParkingRuleVacation> getParkingRuleVacation(Long aptId);
	
	void addVacation(Map<String, Object> request);
	
    int updateParkingRule(@Param("parkingRuleId") Integer parkingRuleId, @Param("parkingRule") ParkingRule parkingRule);
}
