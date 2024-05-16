package com.gailab.parking.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.SpecificRate;

@Mapper
public interface SpecificRateRepository {
	public SpecificRate getSpecificRate(Integer addressId);
	
	List<SpecificRate> getAllSpecificRate(Integer aptId);
}
