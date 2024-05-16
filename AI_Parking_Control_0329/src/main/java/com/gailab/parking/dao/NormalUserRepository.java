package com.gailab.parking.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.NormalUser;

@Mapper
public interface NormalUserRepository {
	NormalUser getUser(String email);
	
	void register(NormalUser normalUser);

	void addUserNameAndContact(NormalUser normalUser);

	void approveMasterUser(Integer userId);

	void updateAddress(Map<String, Object> addressRequestInfo); 
}
