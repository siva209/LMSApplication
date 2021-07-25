package com.bridgelabz.lms.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.repository.CandidateOnBoardingRepository;
import com.bridgelabz.lms.response.Response;

@Service
public class CandidateOnBoardingServiceImpl implements CandidateOnBoardingService{

	
	@Autowired
	 private CandidateOnBoardingRepository candidaterepo;
	
	@Autowired
	private ModelMapper modelmapper;
	@Override
	public Response createUser(CandidateHiredDTO dto) {
		CandidateOnboardingDetails user=modelmapper.map(dto, CandidateOnboardingDetails.class);
		return new Response("regitration sucess",user,201,"true");
		
		
		
	}

}
