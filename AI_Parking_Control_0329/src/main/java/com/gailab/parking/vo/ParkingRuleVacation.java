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
public class ParkingRuleVacation {
	@Id
	private Integer parkingRuleVacationId;
	
	private Integer parkingRuleId;
	
	private Date parkingRuleDate;
	
	private String parkingRuleDescription;
	
	private Integer parkingRuleEveryYear;
}
