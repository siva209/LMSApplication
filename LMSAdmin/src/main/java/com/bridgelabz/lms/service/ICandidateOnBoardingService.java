package com.bridgelabz.lms.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;
import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.HiringCandidate;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.response.Response;
@Service
public interface ICandidateOnBoardingService {
	 public Response createUser(String token,CandidatEOnBoardingDTO dto)throws CandidateRegistrationException;
	 public Response getAllOnBoardingcandidates(String token)throws CandidateRegistrationException;
	 public Response getOnBoardCandidate(String token,Long id)throws CandidateRegistrationException;
	 public Response updateOnBoardingCandidate(String token,Long id,CandidatEOnBoardUpdateDTO dto)throws CandidateRegistrationException;
	 public void deleteOnBoardingCandidateById(String token,Long id)throws CandidateRegistrationException ;
	 public Response updateStatus(String token, Long id, String keyText)throws CandidateRegistrationException;
	 public  Response getCount(String token)throws CandidateRegistrationException;
	    
}