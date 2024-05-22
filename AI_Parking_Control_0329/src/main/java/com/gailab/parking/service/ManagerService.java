package com.gailab.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gailab.parking.dao.ManagerRepository;
import com.gailab.parking.dto.ManagerDTO;
import com.gailab.parking.dto.NormalUserDTO;
import com.gailab.parking.vo.Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ManagerService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("-----loadUserByUsername-----");
        System.out.println(username);
        Manager manager = managerRepository.getUser(username);
        System.out.println(manager);

        if (manager == null) {
            throw new UsernameNotFoundException("Not Found");
        }
        
        ManagerDTO managerDTO = new ManagerDTO(
                manager.getId(),
                manager.getLoginId(),
                manager.getLoginPassword(),
                manager.getEmail(),
                manager.getAptId(),
                manager.getName(),
                manager.getContact(),
                manager.getCreateTime());
        
        log.info("-----managerDTO-----");
        log.info(managerDTO);

        return managerDTO;
    }
}


