package com.bridgelabz.lms.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.HiringCandidate;
import com.bridgelabz.lms.model.Status;
import com.bridgelabz.lms.repository.StatusRepository;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.util.Jms;
import com.bridgelabz.lms.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class StatusServiceImpl implements IStatusService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private StatusRepository statusrepo;

	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	private Jms jms;
	
	@Autowired
	private Status status;
	

	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@Override
	public Response addingStatusDetails(String token,StatusDTO dto) {
		Status verify = restTemplate.getForObject("http://UserRegistration/verifyemail/"+token, Status.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		Status AddDetails = modelmapper.map(dto, Status.class);
		System.out.println(AddDetails);
		statusrepo.save(AddDetails);
		return new Response("Added Status: ", AddDetails,201,"true");
	}else {
		throw new CandidateRegistrationException("invalid status details", null, 400, "true");
	}
		
	}
	@Override
	public Response getAllStatus(String token) {
		Status verify = restTemplate.getForObject("http://UserRegistration/verifyemail/"+token, Status.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		List<Status> isPresent = statusrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of all candidates status are",isPresent,200,"true");
	}
		else {
			throw new CandidateRegistrationException("invalid status details", null, 400, "true");
		}
			
		}

	
	@Override
	public Response updateCandidateStatus(String token,Long id, String  keyText) {
		Status verify = restTemplate.getForObject("http://UserRegistration/verifyemail/"+token, Status.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		Optional<Status> isUserPresent = statusrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setKeyText(keyText);
			System.out.println(isUserPresent);
			statusrepo.save(isUserPresent.get());
			return new Response(" sucessfullu update ", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
		}else
		{
		throw new CandidateRegistrationException("invalid details", null, 400, "true");
	}
	}


	@Override
	public void deleteCandidaStatusd(String token,Long id) {
		Status verify = restTemplate.getForObject("http://UserRegistration/verifyemail/"+token, Status.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		Optional<Status> isUserPresent = statusrepo.findById(id);
		if (isUserPresent.isPresent()) {
			statusrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate to be Delete Not found",null,404,"true");
		}
		
	}
	}
}
	