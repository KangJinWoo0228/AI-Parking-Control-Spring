package com.gailab.parking.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.ParkingRuleRepository;
import com.gailab.parking.vo.ParkingRule;
import com.gailab.parking.vo.ParkingRuleVacation;

@Service
public class ParkingRuleService {
	@Autowired
	private ParkingRuleRepository parkingRuleRepository;
	
	public List<ParkingRule> getParkingRule(Long aptId) {
		return parkingRuleRepository.getParkingRule(aptId);
	}
	
	public List<ParkingRuleVacation> getParkingRuleVacation(Long aptId) {
		return parkingRuleRepository.getParkingRuleVacation(aptId);
	}
	
    public boolean updateParkingRule(Integer parkingRuleId, ParkingRule parkingRule) {
        int existingRules = parkingRuleRepository.updateParkingRule(parkingRuleId, parkingRule);
        return existingRules == 1;
    }
	
}	
