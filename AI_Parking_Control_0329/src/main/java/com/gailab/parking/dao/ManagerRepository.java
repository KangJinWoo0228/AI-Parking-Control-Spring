package com.gailab.parking.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.dto.ManagerDTO;

@Mapper
public interface ManagerRepository {
	ManagerDTO getUser(String username);
}
