package com.bridgelabz.lms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.dto.StatusDTO;

import com.bridgelabz.lms.exception.CandidateRegistrationException;

import com.bridgelabz.lms.model.Status;

import com.bridgelabz.lms.repository.StatusRepository;

public class IStatusServiceImpl implements IStatusService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private StatusRepository statusrepo;
	@Override
	public ResponseDTO getAllStatus() {
		List<Status> isUserPresent = statusrepo.findAll();
		return new ResponseDTO("List of all Status : ", isUserPresent);
	}

	@Override
	public ResponseDTO updateCandidateStatus(Long id, StatusDTO dto) {
		Optional<Status> isUserPresent = statusrepo.findById(dto.getId());
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setCreateUser(dto.getCreatedUser());
			isUserPresent.get().setCurrentStatus(dto.getCurrentStatus());
			isUserPresent.get().setKetText(dto.getKeyText());
			isUserPresent.get().setKeyType(dto.getKeyType());
			isUserPresent.get().setKeyValue(dto.getKeyValue());
			isUserPresent.get().setLastUpdatedUser(dto.getLastUpdatedUser());
			isUserPresent.get().setSequenceNumber(dto.getSequenceNumber());
			System.out.println(isUserPresent);
			statusrepo.save(isUserPresent.get());
			return new ResponseDTO("Status Data Successfully Updated", isUserPresent);
		} else {
			throw new CandidateRegistrationException(400, "Status Data not Updated");
		}
	}

	@Override
	public void deleteCandidaStatusd(Long id) {
		Optional<Status> isUserPresent = statusrepo.findById(id);
		if (isUserPresent.isPresent()) {
			statusrepo.deleteById(id);
		} else {
			new CandidateRegistrationException(400, "Status to be Delete Not found");
		}
	}

	@Override
	public ResponseDTO addingStatusDetails(StatusDTO dto) {
		Status addStatusDetails = modelmapper.map(dto, Status.class);
		System.out.println(addStatusDetails);
		statusrepo.save(addStatusDetails);
		return new ResponseDTO("Added Status: ", addStatusDetails);
	}

}
