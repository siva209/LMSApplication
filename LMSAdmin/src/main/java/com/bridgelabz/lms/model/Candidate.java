package com.bridgelabz.lms.model;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "candidates")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
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
	//private BankInfo bankInfo;
	//private String qualificationInfo;
	
	
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_Id", referencedColumnName = "id")
	private BankInfo hiringBankInfo;
	
  
	@Column(name = "is_verify_email ", columnDefinition = "boolean default false")
	private boolean verifyEmail;


}

