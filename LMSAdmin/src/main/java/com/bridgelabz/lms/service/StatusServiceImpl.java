package com.bridgelabz.lms.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
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
	
	
	@Override
	public Response addingStatusDetails(StatusDTO dto) {
		
		Status AddDetails = modelmapper.map(dto, Status.class);
		System.out.println(AddDetails);
		statusrepo.save(AddDetails);
		return new Response("Added Status: ", AddDetails,201,"true");
	}

	@Override
	public Response getAllStatus() {
		List<Status> isPresent = statusrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of all candidates status are",isPresent,200,"true");
	}

	
	@Override
	public Response updateCandidateStatus(Long id, String  keyText) {
		Optional<Status> isUserPresent = statusrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setKeyText(keyText);
			System.out.println(isUserPresent);
			statusrepo.save(isUserPresent.get());
			return new Response(" sucessfullu update ", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}


	@Override
	public void deleteCandidaStatusd(Long id) {
		Optional<Status> isUserPresent = statusrepo.findById(id);
		if (isUserPresent.isPresent()) {
			statusrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate to be Delete Not found",null,404,"true");
		}
		
	}

	

}
	