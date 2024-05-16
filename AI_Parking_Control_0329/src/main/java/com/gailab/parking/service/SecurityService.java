package com.gailab.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.SecurityRepository;
import com.gailab.parking.vo.Security;

@Service
public class SecurityService {
	@Autowired
	SecurityRepository securityRepository;
	
	public List<Security> getAllSecurities(String aptId){
		return securityRepository.getAllSecurities(aptId);
	}
	
    public int updateSecurity(Security security) {
        return securityRepository.updateSecurity(security);
    }
}
