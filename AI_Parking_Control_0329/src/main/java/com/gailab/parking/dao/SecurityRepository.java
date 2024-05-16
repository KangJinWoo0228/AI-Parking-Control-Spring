package com.gailab.parking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.Security;

@Mapper
public interface SecurityRepository {
	List<Security> getAllSecurities(String aptId);
	int updateSecurity(Security security);
}
