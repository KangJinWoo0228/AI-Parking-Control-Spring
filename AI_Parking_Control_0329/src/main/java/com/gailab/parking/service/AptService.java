package com.gailab.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gailab.parking.dao.AptRepository;
import com.gailab.parking.dao.NormalUserRepository;
import com.gailab.parking.vo.Apt;
import com.gailab.parking.vo.AptAddress;
import com.gailab.parking.vo.Request;
import com.gailab.parking.vo.NormalUser;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class AptService {
	@Autowired
	private AptRepository aptRepository;
	
	@Autowired
	private NormalUserRepository normalUserRepository;
	
	public List<Apt> getAllApts() {
		return aptRepository.getApts();
	}

	public List<String> getAptDong(String aptId) {
		return aptRepository.getAptDong(aptId);
	}

	public List<String> getAptHo(String dong) {
		return aptRepository.getAptHo(dong);
	}
	
	public List<AptAddress> getAptAddress(String aptId){
		return aptRepository.getAptAddress(aptId);
	}
	
	public List<Request> getRequest(String addressId){
		return aptRepository.getRequest(addressId);
	}
	
	@Transactional
	public void addressRequest(Map<String, Object> request) {
		AptAddress address = new ObjectMapper().convertValue(request.get("aptAddress"), AptAddress.class);
		NormalUser normalUser = new ObjectMapper().convertValue(request.get("normalUser"), NormalUser.class);
		Map<String, Object> addressRequestInfo = new HashMap<String, Object>();
		addressRequestInfo.put("aptAddressId", address.getAptId());
		addressRequestInfo.put("normalUserId", normalUser.getId());
		addressRequestInfo.put("dong", address.getDong());
		addressRequestInfo.put("ho", address.getHo());
		aptRepository.addAddressRequest(addressRequestInfo);
		System.out.println(address);
		System.out.println(normalUser);
		System.out.println(addressRequestInfo);
		normalUserRepository.updateAddress(addressRequestInfo);
		normalUserRepository.addUserNameAndContact(normalUser);
	}

	public List<String> getUserAddresses(int userId) {
		return aptRepository.getUserAddresses(userId);
	}
	
	public List<NormalUser> getHouseholdMember(String addressId) {
		return aptRepository.getHouseholdMember(addressId);
	}
}
