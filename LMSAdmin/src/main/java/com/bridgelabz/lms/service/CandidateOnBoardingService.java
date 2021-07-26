package com.bridgelabz.lms.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;
import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.response.Response;
@Service
public interface CandidateOnBoardingService {
	public Response createUser(CandidatEOnBoardingDTO dto);
	public Response getAllOnBoardingcandidates();
    public CandidateOnboardingDetails verify(String token);
    public Response updateOnBoardingCandidate(Long id,CandidatEOnBoardingDTO dto);
    public void deleteCandidateHiringById(Long id) ;
	


}
