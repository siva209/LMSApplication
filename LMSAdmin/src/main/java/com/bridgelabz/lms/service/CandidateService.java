package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.response.Response;
@Service
public interface CandidateService {
	public Response registerCandidate(String token,CandidateHiredDTO dto);
    public Response getAllHiringCandidate(String token);
    public Response getCandidate(String token,Long id);
    public Candidate getCandidateProfileById(Long id);
    public Response updateCandidate(Long id,UpdateHiringDto dto);
    public void deleteCandidateHiringById(String token,Long id) ;
    public  Response updateHiringStatus(String token, Long id, String keyText);
    public Response jobOfferNotificationMail(String token, String email);
    public  Response getCount(String token);
    
}