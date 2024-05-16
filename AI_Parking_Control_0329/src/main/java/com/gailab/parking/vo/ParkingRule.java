package com.gailab.parking.vo;

import java.sql.Date;
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
public class ParkingRule {
	@Id
	private Integer parkingRuleId;
	
	private Integer aptId;
	
	private Integer parkingRuleBaseTime;
	
	private Integer parkingRuleApplyTime;
	
	private Integer parkingRuleFee;
	
	private Time parkingRuleStartTime;
	
	private Time parkingRuleEndTime;
	
	private Date parkingRuleApplyStart;
	
	private Date parkingRuleApplyEnd;
	
}
