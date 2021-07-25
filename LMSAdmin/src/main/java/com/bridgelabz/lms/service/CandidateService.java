package com.bridgelabz.lms.service;

import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.UserDTO;
import com.bridgelabz.lms.exception.InvalidDetailsException;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.response.Response;


@Service
public interface CandidateService {
	Response registerCandidate(UserDTO dto) throws InvalidDetailsException;
	public Candidate verify(String token);
	public Response getAllHiredcandidates();


}
