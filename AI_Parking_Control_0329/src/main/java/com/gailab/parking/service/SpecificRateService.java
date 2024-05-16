package com.gailab.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.SpecificRateRepository;
import com.gailab.parking.vo.SpecificRate;

@Service
public class SpecificRateService {
	@Autowired
	SpecificRateRepository specificRateRepository;
	
	public SpecificRate getSpecificRate(Integer addressId) {
		return specificRateRepository.getSpecificRate(addressId);
	}
	
	public List<SpecificRate> getAllSpecificRate(Integer aptId) {
		return specificRateRepository.getAllSpecificRate(aptId);
	}
}
