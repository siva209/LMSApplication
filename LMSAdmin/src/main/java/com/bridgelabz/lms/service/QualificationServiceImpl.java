package com.bridgelabz.lms.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.dto.UpdateQualificationDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.QualificationInfo;
import com.bridgelabz.lms.repository.QualificationRepository;
import com.bridgelabz.lms.response.Response;
@Service
public class QualificationServiceImpl implements QualificationService{
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private QualificationRepository qualificationrepo;
	
	@Override
	public Response addingQualificationDetails(QualificationDto dto) {
		QualificationInfo qualificationDetails=modelmapper.map(dto, QualificationInfo.class);
		System.out.println(qualificationDetails);
		qualificationrepo.save(qualificationDetails);
		return new Response("Added Status: ", qualificationDetails,201,"true");
	}


	@Override
	public Response getAllQualificationDeatils() {
		List<QualificationInfo> isPresent = qualificationrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of all candidates Qualification Details are",isPresent,200,"true");
	}
	

	@Override
	public Response updateQualification(Integer id, UpdateQualificationDto dto) {
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
			return new Response("regitration sucess", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}

	@Override
	public void deleteQualification(Integer id) {
		Optional<QualificationInfo> isUserPresent = qualificationrepo.findById(id);
		if (isUserPresent.isPresent()) {
			qualificationrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate to be Delete Not found",null,404,"true");
		}
	}
}
