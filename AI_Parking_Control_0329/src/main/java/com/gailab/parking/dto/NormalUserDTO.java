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
public class NormalUserDTO extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String loginEmail;

	private String loginPassword;

	private Long addressId;

	private boolean addressMaster;

	private boolean approval;

	private Date createTime;
	
	private String name;
	
	private String contact;
	
	private final static List<String> roleNames = new ArrayList<>();
	

	public NormalUserDTO(Long id, String loginEmail, String loginPassword, Long addressId,
			boolean addressMaster, boolean approval, Date createTime, String name, String contact) {
		
		super(loginEmail, loginPassword, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
		
		this.id = id;
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
		this.addressId = addressId;
		this.addressMaster = addressMaster;
		this.approval = approval;
		this.createTime = createTime;
		this.name = name;
		this.contact = contact;
	};
	
	public Map<String, Object> getClaims() {
		Map<String, Object> dataMap = new HashMap<>();
		
		dataMap.put("id", id);
		dataMap.put("loginEmail", loginEmail);
		dataMap.put("loginPassword", loginPassword);
		dataMap.put("addressId", addressId);
		dataMap.put("addressMaster", addressMaster);
		dataMap.put("approval", approval);
		dataMap.put("createTime", createTime);
		dataMap.put("name", name);
		dataMap.put("contact", contact);
		
		return dataMap;
	}
}
