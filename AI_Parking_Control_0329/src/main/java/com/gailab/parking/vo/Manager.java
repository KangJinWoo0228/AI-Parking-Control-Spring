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
public class Manager {
	@Id
	private Long id;

	private String loginId;

	private String loginPassword;

	private String email;

	private Long aptId;

	private String name;

	private String contact;
	
	private Date createTime;
	
}
