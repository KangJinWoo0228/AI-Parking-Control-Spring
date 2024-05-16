package com.gailab.parking.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Request {
	@Id
	private Long addRequestId;
	
	private int aptAddressId;
	
	private int userId;
	
	private int delFlag;
	
	private String userLoginEmail;
	
	private int userMaster;
	
	private String userName;
	
	private String userContact;
}
