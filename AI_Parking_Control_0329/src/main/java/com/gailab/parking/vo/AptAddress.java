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
public class AptAddress {
	@Id
	private Long id;
	
	private String aptId;
	
	private String addressId;
	
	private String dong;
	
	private String ho;
	
	private Long master;
	
	private String contact;
}
