package com.gailab.parking.vo;

import java.sql.Time;

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
public class Security {
	@Id
	private Long id;
	
	private String aptId;
	
	private String securityLoginId;
	
	private String securityLoginPassword;
	
	private String securityName;
	
	private String securityContact;
	
	private String securityArea;
	
	private Time securityStartTime;
	
	private Time securityEndTime;
}
