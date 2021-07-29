package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.response.Response;
@Service
public interface CandidateService {
	public Response registerCandidate(CandidateHiredDTO dto);
    public Response getAllHiredcandidates();
    public Candidate verify(String token);
    public Candidate getCandidateProfileById(Long id);
    public Response updateCandidate(Long id,UpdateHiringDto dto);
    public void deleteCandidateHiringById(Long id) ;
    
    
}