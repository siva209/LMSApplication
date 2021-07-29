package com.bridgelabz.lms.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class UpdateBankDto {
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
