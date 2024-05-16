package com.gailab.parking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.InoutRepository;
import com.gailab.parking.vo.InoutHistory;

@Service
public class InoutService {
	@Autowired
	private InoutRepository inoutRepository;
	
	public List<Map<String, Object>> getAddressInoutHistory(Long addressId) {
		return inoutRepository.getAddressInoutHistory(addressId);
	}
	
	public List<InoutHistory> getAllInoutHistories(String aptId){
		return inoutRepository.getAllInoutHistories(aptId);
	}
}
