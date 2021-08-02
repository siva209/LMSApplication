package com.bridgelabz.lms.service;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;

import java.util.Iterator;
import java.util.List;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.repository.CandidateOnBoardingRepository;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.util.Jms;
import com.bridgelabz.lms.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CandidateOnBoardingServiceImpl implements CandidateOnBoardingService{
     @Autowired
	 private CandidateOnBoardingRepository candidateonboardrepo;
	
	@Autowired
	private JwtUtil jwt;
	@Autowired
	private Jms jms;
	
	@Autowired
	private ModelMapper modelmapper;
	
  @Autowired
  private CandidateOnboardingDetails details;
  
	
	/**
	 * Get All OnBoardingCandidates: used to display all the OnBoardingCandidates in the table
	 * @param token
	 * @return response of list of OnBoardingCandidates
	 */
	
  
	@Override
	public Response getAllOnBoardingcandidates(String token) {
		//int Id = tokenutil.decodeToken(token);
		List<CandidateOnboardingDetails> isPresent = candidateonboardrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of Onboarding Candidates are",isPresent,200,"true");
	}
	
	/**
	 * Get  OnBoardingCandidates by id: get the  hiring candidates based upon candidate id in the table
	 * @param token,id
	 * @return response of specified  user
	 */
	

	@Override
	public Response getOnBoardCandidate(String token, Long id) {
		// int Id = tokenutil.decodeToken(token);
		Optional<CandidateOnboardingDetails> isUserPresent = candidateonboardrepo.findById(id);
		CandidateOnboardingDetails candidates = isUserPresent.get();
		return new Response("List of OnBoarding Candidates are", isUserPresent, 200, "true");
	}
	

	/**
	 * Register User : used to register the user
	 * @param dto,token
	 * @return register response
	 */
	
	@Override
	public Response createUser(String token,CandidatEOnBoardingDTO dto) {
		CandidateOnboardingDetails candidateDetails = modelmapper.map(dto, CandidateOnboardingDetails.class);
		System.out.println(candidateDetails);
		candidateonboardrepo.save(candidateDetails);
		return new Response("Added Status: ", candidateDetails,201,"true");
	}
	

	/**
	 * Update OnBoardingCandidates : set new data for OnBoardingCandidates
	 * @param CandidatEOnBoardUpdateDTO
	 * @return response of OnBoardingCandidates
	 */

	@Override
	public Response updateOnBoardingCandidate(String token,Long id, CandidatEOnBoardUpdateDTO dto) {
		Optional<CandidateOnboardingDetails> isUserPresent = candidateonboardrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setFirstName(dto.getFirstName());
			isUserPresent.get().setMiddleName(dto.getMiddleName());
			isUserPresent.get().setLastName(dto.getLastName());
			isUserPresent.get().setEmail(dto.getEmail());
			isUserPresent.get().setMobileNum(dto.getMobileNum());
			isUserPresent.get().setHiredCity(dto.getHiredCity());
			isUserPresent.get().setDegree(dto.getDegree());
			isUserPresent.get().setHiredLab(dto.getHiredLab());;
			isUserPresent.get().setKnowledgeRemark(dto.getKnowledgeRemark());
			System.out.println(isUserPresent);
			candidateonboardrepo.save(isUserPresent.get());
			return new Response("Updated OnBoarding Candidates  Successfully", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid OnBoarding Candidates details", null, 400, "true");
		}
	}

	/**
	 * Delete OnBoardingCandidates: used to delete the present user
	 * @param id
	 * @return response of deleted or not
	 */

	@Override
	public void deleteOnBoardingCandidateById(String token,Long id) {
		// int Id = tokenutil.decodeToken(token);	

		Optional<CandidateOnboardingDetails> isUserPresent = candidateonboardrepo.findById(id);
		if (isUserPresent.isPresent()) {
			candidateonboardrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate to be Delete Not found",null,404,"true");
		}
	}

	/**
	 * Update OnBoardingCandidates : set new keyText for OnBoardingCandidates
	 * @param id,KeyText
	 * @return response of KeyText
	 */
	

	@Override
	public Response updateStatus(String token, Long id, String keyText) {
		Optional<CandidateOnboardingDetails> isUserPresent = candidateonboardrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setStatus(keyText);
			candidateonboardrepo.save(isUserPresent.get());
			return new Response("Candidate status updated Successfully ", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}
	/**
	 * Count OnBoardingCandidates : calculate the OnBoardingCandidates
	 * @param id
	 * @return response of count number of OnBoardingCandidates 
	 */

	@Override
	public Response getCount(String token) {
		// int Id = tokenutil.decodeToken(token);
		List<CandidateOnboardingDetails> isUserPresent = candidateonboardrepo.findAll();
		long count = 0;
		for (Iterator iterator = isUserPresent.iterator(); iterator.hasNext();) {
			CandidateOnboardingDetails lmsHiring = (CandidateOnboardingDetails) iterator.next();
			count++;
		}
		return new Response("Number of Candidates : ", count,201,"true");
	}


	}



