package com.bridgelabz.lms.appconfiguration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.model.Status;
import com.bridgelabz.lms.util.JwtUtil;




@Configuration
public class AppConfig {
	@Bean
	public BCryptPasswordEncoder bcryptpasswordencoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public JwtUtil jwtoperations()
	{
		return new JwtUtil();
	}
	@Bean
	public Candidate candidate()
	{
		return new Candidate();
	}
	@Bean
	public CandidateOnboardingDetails onboardDetails() {
		return new CandidateOnboardingDetails();
		
	}
	@Bean
	public Status statusdetails() {
		return new  Status();
	}
	@Bean
	public ModelMapper modelMapper() {
	ModelMapper modelMapper = new ModelMapper();
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	return modelMapper;
	}

}

