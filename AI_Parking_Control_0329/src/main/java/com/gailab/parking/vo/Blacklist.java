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
public class Blacklist {
	@Id
	private Long id;
	
	private String vehicleOwnerName;
	
	private String vehicleOwnerContact;
	
    private String aptAddressDong;
    
    private String aptAddressHo;
    
    private String vehicleNumber;
    
    private Date blacklistReportTime;
    
    private String blacklistDescription;
    
    private int blacklistCount;
    
    private String inoutPicture;
}
