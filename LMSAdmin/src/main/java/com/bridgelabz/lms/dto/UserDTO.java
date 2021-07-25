package com.bridgelabz.lms.dto;

import lombok.Data;

@Data
public class UserDTO {
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNum;
	private String parentMobile;
	private String temporaryAddress;
	private String parentOccupation;
	private String parentAnnualSalary;
	private String permanentAddress;
}