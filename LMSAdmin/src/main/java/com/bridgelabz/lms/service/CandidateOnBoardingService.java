package com.bridgelabz.lms.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;
import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.response.Response;
@Service
public interface CandidateOnBoardingService {
	 public Response createUser(String token,CandidatEOnBoardingDTO dto);
	 public Response getAllOnBoardingcandidates(String token);
	 public Response getOnBoardCandidate(String token,Long id);
	 public Response updateOnBoardingCandidate(String token,Long id,CandidatEOnBoardUpdateDTO dto);
	 public void deleteOnBoardingCandidateById(String token,Long id) ;
	 public Response updateStatus(String token, Long id, String keyText);
	    
}