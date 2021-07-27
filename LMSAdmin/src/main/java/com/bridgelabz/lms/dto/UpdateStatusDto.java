package com.bridgelabz.lms.dto;

import lombok.Data;

@Data
public class UpdateStatusDto {
	private String createdUser;
	private String currentStatus;
	private int id;
	private String keyText;
    private String keyType;
	private String keyValue;
	private String lastUpdatedUser;
	private String sequenceNumber;

}
