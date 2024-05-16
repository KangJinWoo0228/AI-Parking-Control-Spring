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
public class InoutHistory {
//	tbl_inout
	@Id
	private Long id;
	
	private Long vehicleId;
	
	private String inoutType;
	
	private Date inoutTime;
	
	private String inoutPicture;
	
//	입출차 정보 제공에 필요한 column
	private String vehicleNumber;
	
	private String vehicleType;
	
	private String aptAddressDong;
	
	private String aptAddressHo;
	
	private String vehicleOwnerName;
	
	private String vehicleOwnerContact;
}
