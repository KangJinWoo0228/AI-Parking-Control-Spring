package com.gailab.parking.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.gailab.parking.dao.AptRepository;
import com.gailab.parking.dao.NormalUserRepository;
import com.gailab.parking.dto.NormalUserDTO;
import com.gailab.parking.vo.NormalUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NormalUserService implements UserDetailsService {
	@Autowired
	private NormalUserRepository normalUserRepository;
	
	@Autowired
	private AptRepository aptRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("-----loadUserByUsername-----");
		
		NormalUser normalUser = normalUserRepository.getUser(username);
		System.out.println(normalUser);
		
		if (normalUser == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		
		NormalUserDTO normalUserDTO = new NormalUserDTO(
				normalUser.getId(),
				normalUser.getLoginEmail(),
				normalUser.getLoginPassword(),
				normalUser.getAddressId(),
				normalUser.isAddressMaster(),
				normalUser.isApproval(),
				normalUser.getCreateTime(),
				normalUser.getName(),
				normalUser.getContact());
		
		log.info("-----normalUserDTO-----");
		log.info(normalUserDTO);
		
		return normalUserDTO;
	}
	
	@Transactional
	public NormalUserDTO getKakaoUser(String accessToken) {
		String email = getEmailFromKakaoAccessToken(accessToken);
		
		NormalUser normalUser = normalUserRepository.getUser(email);
		
		if (normalUser != null) {
			NormalUserDTO normalUserDTO = new NormalUserDTO(
					normalUser.getId(),
					normalUser.getLoginEmail(),
					normalUser.getLoginPassword(),
					normalUser.getAddressId(),
					normalUser.isAddressMaster(),
					normalUser.isApproval(),
					normalUser.getCreateTime(),
					normalUser.getName(),
					normalUser.getContact());
			
			return normalUserDTO;
		}
		
		NormalUser socialUser = makeSocialUser(email);
		normalUserRepository.register(socialUser);
		NormalUserDTO normalUserDTO = new NormalUserDTO(
				socialUser.getId(),
				socialUser.getLoginEmail(),
				socialUser.getLoginPassword(),
				socialUser.getAddressId(),
				socialUser.isAddressMaster(),
				socialUser.isApproval(),
				socialUser.getCreateTime(),
				socialUser.getName(),
				socialUser.getContact());
		
		return normalUserDTO;
	}
	
	private String getEmailFromKakaoAccessToken(String accessToken) {
		String kakaoGetUserURL = "https://kapi.kakao.com/v2/user/me";
		
		if (accessToken == null) {
			throw new RuntimeException("Access Token is null");
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(kakaoGetUserURL).build();
		
		ResponseEntity<LinkedHashMap> response = restTemplate.exchange(
				uriBuilder.toString(), 
				HttpMethod.GET,
				entity,
				LinkedHashMap.class);
		
		log.info(response);
		
		LinkedHashMap<String, LinkedHashMap> bodyMap = response.getBody();
		
		log.info("---------------");
		log.info(bodyMap);
		
		LinkedHashMap<String, String> kakaoAccount = bodyMap.get("kakao_account");
		
		log.info("kakaoAccount: " + kakaoAccount);
		
		return kakaoAccount.get("email");
	}
	
	private String makeTempPassword() {
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < 10; i++) {
			buffer.append((char)((int)(Math.random() * 55) + 65));
		}
		
		return buffer.toString();
	}
	 
	private NormalUser makeSocialUser(String email) {
		String tempPassword = makeTempPassword();
		
		log.info("tempPassword: " + tempPassword);
		
		NormalUser normalUser = NormalUser.builder()
				.loginEmail(email)
				.loginPassword(passwordEncoder.encode(tempPassword))
				.build();
		
		return normalUser;
	}

	@Transactional
	public void approveMasterUser(Map<String, Object> request) {
		Integer userId = (Integer) request.get("normalUserId");
		System.out.println(userId);
		System.out.println("왜이래" + request);
		normalUserRepository.approveMasterUser(userId);
		aptRepository.updateAddressMaster(request);
		aptRepository.deleteInfo(request);
		aptRepository.setSpecificRate((Integer) request.get("addressId"));
	}
}
