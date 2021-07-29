package com.bridgelabz.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="bank_info")
public class BankInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String panNumber;
	private String aadharNumber;
	private String bankName;
	private String bankAccountNumber;
	private String ifscCode;
	private String passbookPath;
	private String panPath;
	private String aadharPath;
	private LocalDateTime creatorStamp;
	private LocalDateTime updateStamp;
}

