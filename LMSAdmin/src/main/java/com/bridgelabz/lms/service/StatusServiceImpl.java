package com.bridgelabz.lms.service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.dto.UpdateStatusDto;
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
	private JwtUtil jwt=new JwtUtil();
	
	@Autowired
	private Jms jms;
	
	@Override
	public Response addingStatusDetails(StatusDTO dto) {
		Optional<Status>isuserprsent=statusrepo.findById(dto.getId());
		if(isuserprsent.isPresent()) {
			throw new CandidateRegistrationException("invalid details",null,400,"true");
		}
		else {
			Status user=modelmapper.map(dto, Status.class);
			statusrepo.save(user);
		log.info(user.getCurrentStatus()+" current status"+"date:"+user.getKetText());
		String body="http://localhost:8081/verifyemail/"+jwt.jwtToken(user.getId());
		System.out.println(body);
		jms.sendEmail(user.getId(),"verification email",body);
		return new Response("regitration sucess",user,201,"true");
	}
	
	}

	@Override
	public Response getAllStatus() {
		List<Status> isPresent = statusrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of all candidates status are",isPresent,200,"true");
	}
	

	@Override
	public Status verify(String token) {
		long id=jwt.parseJWT(token);
		log.debug(token);
		Status user=statusrepo.isIdExists(id).orElseThrow(() -> new CandidateRegistrationException("user not exists",HttpStatus.OK,id,"false"));
		user.setVerifyEmail(true);
		statusrepo.save(user);
		return user;
	}
	

	@Override
	public Response updateCandidateStatus(Long id, UpdateStatusDto dto) {
		Optional<Status> isUserPresent = statusrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setCurrentStatus(dto.getCurrentStatus());
			isUserPresent.get().setKetText(dto.getKeyText());
			isUserPresent.get().setKeyType(dto.getKeyType());
			isUserPresent.get().setLastUpdatedUser(dto.getLastUpdatedUser());
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
	