package com.gailab.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gailab.parking.service.SecurityService;
import com.gailab.parking.vo.Security;

@RestController
public class SecurityController {
	@Autowired
	SecurityService securityService;
	
	@GetMapping("/api/securities/{aptId}")
	public List<Security> getAllSecurities(@PathVariable("aptId") String aptId){
			return securityService.getAllSecurities(aptId);
	}
	
    @PostMapping("/api/securities/update")
    public int updateSecurity(@RequestBody Security security) {
        return securityService.updateSecurity(security);
    }
}
