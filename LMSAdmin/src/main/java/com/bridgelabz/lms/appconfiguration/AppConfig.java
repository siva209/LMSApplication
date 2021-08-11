package com.bridgelabz.lms.appconfiguration;
import javax.annotation.PostConstruct;
import java.util.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.model.Status;
import com.bridgelabz.lms.util.JwtUtil;

@Configuration
public class AppConfig {
	
	private static MessageSourceAccessor messageSourceAccessor;
	
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
	
	@PostConstruct
	private void initMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/errormessages", "classpath:messages/successmessages");
		messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());

	}

	public static MessageSourceAccessor getMessageAccessor() {
		return messageSourceAccessor;
	}
	
	@Bean
	public CandidateOnboardingDetails onboardDetails() {
		return new CandidateOnboardingDetails();
		
	}
	@Bean
	@LoadBalanced
    public RestTemplate restTemplate() {
		return new  RestTemplate();
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

