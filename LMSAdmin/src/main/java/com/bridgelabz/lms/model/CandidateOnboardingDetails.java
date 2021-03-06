package com.bridgelabz.lms.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "candidates_on_boarding")
public class CandidateOnboardingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNum;
	private String hiredCity;
	private String hiredDate;
	private String degree;
	private String hiredLab;
	private String attitudeRemark;
	private String communicationRemark;
	private String knowledgeRemark;
	private String onboardingStatus;
	private String status;
	private String creatorUser;
	private String joindate;
	private String location;
	private double aggrPer;
	private int currentPincode;
	private int permanentPincode;
	@Column(name = "is_verify_email ", columnDefinition = "boolean default false")
	private boolean verifyEmail;

	}
	

	