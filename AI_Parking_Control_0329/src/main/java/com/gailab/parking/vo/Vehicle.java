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
public class Vehicle {
	@Id
	private Long id;
	
	private String vehicleNumber;
	
	private String vehicleType;
	
	private Long addressId;
	
	private String ownerName;
	
	private String ownerContact;
	
	private String addressDong;
	
	private String addressHo;
	
	private boolean monthlyTicket;
	
	private String description;
}
