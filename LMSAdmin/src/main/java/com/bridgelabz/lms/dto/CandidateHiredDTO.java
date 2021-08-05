package com.bridgelabz.lms.dto;

import java.time.LocalDateTime;

import javax.persistence.Id;

import lombok.Data;

@Data
public class CandidateHiredDTO {
	@Id
	 private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNum;
	private String hiredcity;
	private String parentName;
	private String parentMobile;
	private String temporaryAddress;
	private String parentOccupation;
	private String parentAnnualSalary;
	private String permanentAddress;
	private LocalDateTime creatorStamp;
	private LocalDateTime updateStamp;

}
