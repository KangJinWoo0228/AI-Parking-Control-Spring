package com.gailab.parking.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.dto.ManagerDTO;
import com.gailab.parking.vo.Manager;

@Mapper
public interface ManagerRepository {
	Manager getUser(String loginId);
}
