package com.bridgelabz.lms.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.response.Response;
@Service
public interface CandidateOnBoardingService {
	public Response createUser(CandidateHiredDTO dto);

}