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
public class Apt {
	@Id
	private Long id;
	
	private String aptName;
	
	private String address;
	
	private String postalCode;
	
	private int parkingArea;
}
