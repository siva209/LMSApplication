package com.bridgelabz.lms.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.response.Response;
@Service
public interface CandidateOnBoardingService {
	 public Response createUser(CandidatEOnBoardingDTO dto);
	 public Response getAllOnBoardingcandidates();
	 public CandidateOnboardingDetails verify(String token);
	 public Response updateOnBoardingCandidate(Long id,CandidatEOnBoardUpdateDTO dto);
	 public void deleteOnBoardingCandidateById(Long id) ;
	    



}
