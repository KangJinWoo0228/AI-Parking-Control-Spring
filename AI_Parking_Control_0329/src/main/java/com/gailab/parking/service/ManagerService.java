package com.gailab.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.ManagerRepository;
import com.gailab.parking.dto.ManagerDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ManagerService implements UserDetailsService {
	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("-----loadUserByUsername-----");
		System.out.println(username);
		ManagerDTO managerDTO = managerRepository.getUser(username);
		System.out.println(managerDTO);
		
		if (managerDTO == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		
		log.info("-----normalUserDTO-----");
		log.info(managerDTO);
		
		return managerDTO;
	}
}