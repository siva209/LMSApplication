package com.bridgelabz.lms.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.dto.UpdateQualificationDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.QualificationInfo;
import com.bridgelabz.lms.model.Status;
import com.bridgelabz.lms.repository.QualificationRepository;
import com.bridgelabz.lms.response.Response;
@Service
public class QualificationServiceImpl implements IQualificationService{
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private QualificationRepository qualificationrepo;
	

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Response addingQualificationDetails(String token,QualificationDto dto) {
		QualificationInfo verify = restTemplate.getForObject("http://localhost:8080/verifyemail/"+token, QualificationInfo.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		QualificationInfo qualificationDetails=modelmapper.map(dto, QualificationInfo.class);
		System.out.println(qualificationDetails);
		qualificationrepo.save(qualificationDetails);
		return new Response("Added Qualification details: ", qualificationDetails,201,"true");
	}
		else {
			throw new CandidateRegistrationException("invalid status details", null, 400, "true");
		}
			
		}


	@Override
	public Response getAllQualificationDeatils(String token) {
		QualificationInfo verify = restTemplate.getForObject("http://localhost:8080/verifyemail/"+token, QualificationInfo.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		List<QualificationInfo> isPresent = qualificationrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of all candidates Qualification Details are",isPresent,200,"true");
	}
		else {
			throw new CandidateRegistrationException("invalid status details", null, 400, "true");
		}
			
		}

	

	@Override
	public Response updateQualification(String token,Integer id, UpdateQualificationDto dto) {
		QualificationInfo verify = restTemplate.getForObject("http://localhost:8080/verifyemail/"+token, QualificationInfo.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		Optional<QualificationInfo> isUserPresent = qualificationrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setDiploma(dto.isDiploma());
			isUserPresent.get().setCourse(dto.getCourse());
			isUserPresent.get().setAggrPercentage(dto.getAggrPercentage());
			isUserPresent.get().setEnggPercentage(dto.getEnggPercentage());
			isUserPresent.get().setTrainingDuration(dto.getTrainingDuration());
			isUserPresent.get().setTrainingInstitute(dto.getTrainingInstitute());
			isUserPresent.get().setYearOfPassing(dto.getYearOfPassing());
			System.out.println(isUserPresent);
			qualificationrepo.save(isUserPresent.get());
			return new Response("update sucessfuly", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
		}
		else {
			throw new CandidateRegistrationException("invalid status details", null, 400, "true");
		}
			
		}

	@Override
	public void deleteQualification(String token,Integer id) {
		QualificationInfo verify = restTemplate.getForObject("http://localhost:8080/verifyemail/"+token, QualificationInfo.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		Optional<QualificationInfo> isUserPresent = qualificationrepo.findById(id);
		if (isUserPresent.isPresent()) {
			qualificationrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate qualification to be Delete Not found",null,404,"true");
		}
	}
	}
}
