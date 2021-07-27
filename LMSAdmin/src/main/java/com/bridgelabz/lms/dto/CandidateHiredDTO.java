package com.bridgelabz.lms.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CandidateHiredDTO {
	
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
	private String profileImage;
	private String folderId;
	private String status;
	private LocalDateTime creatorStamp;
	private LocalDateTime updateStamp;
	//private String bankInfo;
	//private String qualificationInfo;
  
	

}
