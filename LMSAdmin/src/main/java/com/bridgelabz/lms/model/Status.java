package com.bridgelabz.lms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;
@Data
@Entity
@Table(name="status")
public class Status {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long createUser;
	private String currentStatus;
	private Long id;
	private String KetText;
	private String keyType;
	private String keyValue;
	private String lastUpdatedUser;
	private String sequenceNumber;
	public void setVerifyEmail(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}