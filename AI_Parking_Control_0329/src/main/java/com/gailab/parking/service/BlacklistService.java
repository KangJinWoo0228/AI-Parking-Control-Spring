package com.gailab.parking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.BlacklistRepository;
import com.gailab.parking.vo.Blacklist;

@Service
public class BlacklistService {
	@Autowired
	BlacklistRepository blacklistRepository;
	
    public List<Blacklist> getAllBlacklist(String aptId) {
        return blacklistRepository.getAllBlacklist(aptId);
    }
	
	public void reportBlacklist(Map<String, Object> request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> info = (Map<String, Object>) request.get("info");
		System.out.println("info" + info);
		blacklistRepository.reportBlacklist(info);
	}
}
