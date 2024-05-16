package com.gailab.parking.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ManagerDTO extends User {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String loginId;

	private String loginPassword;

	private String email;

	private Long aptId;

	private String name;

	private String contact;
	
	private Date createTime;
	
	private final static List<String> roleNames = new ArrayList<>();
	

	public ManagerDTO(Long id, String loginId, String loginPassword, String email,
			Long aptId, String name, String contact, Date createTime) {
		
		super(loginId, loginPassword, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
		
		this.id = id;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.email = email;
		this.aptId = aptId;
		this.name = name;
		this.contact = contact;
		this.createTime = createTime;
	};
	
	public Map<String, Object> getClaims() {
		Map<String, Object> dataMap = new HashMap<>();
		
		dataMap.put("id", id);
		dataMap.put("loginId", loginId);
		dataMap.put("loginPassword", loginPassword);
		dataMap.put("email", email);
		dataMap.put("aptId", aptId);
		dataMap.put("name", name);
		dataMap.put("contact", contact);
		dataMap.put("createTime", createTime);
		
		return dataMap;
	}
}
