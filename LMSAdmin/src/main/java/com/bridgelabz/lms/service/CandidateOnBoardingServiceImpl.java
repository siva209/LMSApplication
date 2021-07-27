package com.bridgelabz.lms.service;



import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;
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
	 private CandidateOnBoardingRepository candidaterepo;
	
	
	@Autowired
	private BCryptPasswordEncoder pwdencoder;
	
	@Autowired
	private JwtUtil jwt=new JwtUtil();
	@Autowired
	private Jms jms;
	
	@Autowired
	private ModelMapper modelmapper;
	
  @Autowired
  private CandidateOnboardingDetails details;
	@Override
	public Response getAllOnBoardingcandidates() {
		List<CandidateOnboardingDetails> isPresent = candidaterepo.findAll();
		System.out.println(isPresent);
		return new Response("List of HiredCandidates are",isPresent,200,"true");
	}
	
	
	
	@Override
	public Response createUser(CandidatEOnBoardingDTO dto) {
		Optional<CandidateOnboardingDetails>isuserprsent=candidaterepo.isEmailExists(dto.getEmail());
		if(isuserprsent.isPresent()) {
			throw new CandidateRegistrationException("invalid details",null,400,"true");
		}
		else {
			CandidateOnboardingDetails user=modelmapper.map(dto, CandidateOnboardingDetails.class);
		candidaterepo.save(user);
		log.info(user.getFirstName()+" registered "+"date:"+user.getPermanentPincode());
		String body="http://localhost:8080/verifyemail/"+jwt.jwtToken(user.getId());
		System.out.println(body);
		jms.sendEmail(user.getEmail(),"verification email",body);
		return new Response("regitration sucess",user,201,"true");
	}
	
	}



	@Override
	public CandidateOnboardingDetails verify(String token) {
		long id=jwt.parseJWT(token);
		log.debug(token);
		CandidateOnboardingDetails user=candidaterepo.isIdExists(id).orElseThrow(() -> new CandidateRegistrationException("user not exists",HttpStatus.OK,id,"false"));
		user.setVerifyEmail(true);
		candidaterepo.save(user);
		return user;
	}



	@Override
	public Response updateOnBoardingCandidate(Long id, CandidatEOnBoardUpdateDTO dto) {
		Optional<CandidateOnboardingDetails> isUserPresent = candidaterepo.findById(id);
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
			candidaterepo.save(isUserPresent.get());
			return new Response("regitration sucess", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}



	@Override
	public void deleteOnBoardingCandidateById(Long id) {
		Optional<CandidateOnboardingDetails> isUserPresent = candidaterepo.findById(id);
		if (isUserPresent.isPresent()) {
			candidaterepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate to be Delete Not found",null,404,"true");
		}
	}
}

	