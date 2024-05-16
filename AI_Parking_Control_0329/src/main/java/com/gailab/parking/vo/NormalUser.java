package com.gailab.parking.vo;

import java.sql.Date;

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
public class NormalUser {
	@Id
	private Long id;
	
	private String loginEmail;
	
	private String loginPassword;
	
	private Long addressId;
	
	private boolean addressMaster;
	
	private boolean approval;
	
	private Date createTime;
	
	private String name;
	
	private String contact;
	
}
