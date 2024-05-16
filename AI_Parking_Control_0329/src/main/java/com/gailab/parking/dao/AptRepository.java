package com.gailab.parking.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.Apt;
import com.gailab.parking.vo.AptAddress;
import com.gailab.parking.vo.NormalUser;
import com.gailab.parking.vo.Request;

import java.util.List;
import java.util.Map;

@Mapper
public interface AptRepository {
	List<Apt> getApts();
	
	List<AptAddress> getAptAddress(String aptId);

	List<String> getAptDong(String aptId);

	List<String> getAptHo(String dong);
	
	List<Request> getRequest(String addressId);
	
	void addAddressRequest(Map<String, Object> addressRequestInfo);

	void updateAddressMaster(Map<String, Object> id);

	void deleteInfo(Map<String, Object> request);

	List<String> getUserAddresses(int userId);

	void setSpecificRate(Integer addressId);
	
	List<NormalUser> getHouseholdMember(String addressId);
}
