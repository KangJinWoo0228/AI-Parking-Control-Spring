package com.gailab.parking.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.InoutHistory;

import java.util.List;
import java.util.Map;

@Mapper
public interface InoutRepository {
	public List<Map<String, Object>> getAddressInoutHistory(Long addressId);
	
	List<InoutHistory> getAllInoutHistories(String aptId);
}
