package com.bridgelabz.lms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="qualification_info")
public class QualificationInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private boolean diploma;
	private String degree;
	private String filed;
	private String yearOfPassing;
	private String finalPercentage;
	private String aggrPercentage;
	private String enggPercentage;
	private String finalCertification;
	private String trainingInstitute;
	private String trainingDuration;
	private String course;


}
