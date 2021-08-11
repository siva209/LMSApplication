package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.HiringCandidate;
import com.bridgelabz.lms.response.Response;
@Service
public interface ICandidateHiringService {
	public Response registerCandidate(String token,CandidateHiredDTO dto) throws CandidateRegistrationException;
    public Response getAllHiringCandidate(String token)throws CandidateRegistrationException;
    public Response getCandidate(String token,Long id)throws CandidateRegistrationException;
    public HiringCandidate getCandidateProfileById(Long id)throws CandidateRegistrationException;
    public Response updateCandidate(String token,Long id,UpdateHiringDto dto)throws CandidateRegistrationException;
    public void deleteCandidateHiringById(String token,Long id)throws CandidateRegistrationException ;
    public  Response updateHiringStatus(String token, Long id, String keyText)throws CandidateRegistrationException;
    public Response jobOfferNotificationMail(String token, String email)throws CandidateRegistrationException;
    public  Response getCount(String token)throws CandidateRegistrationException;

    
}