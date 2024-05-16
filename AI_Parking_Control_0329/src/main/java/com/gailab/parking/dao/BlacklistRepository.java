package com.gailab.parking.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gailab.parking.vo.Blacklist;

@Mapper
public interface BlacklistRepository {
	void reportBlacklist(Map<String, Object> request);

	List<Blacklist> getAllBlacklist(String aptId);
}
