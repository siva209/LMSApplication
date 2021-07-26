package com.bridgelabz.lms.service;
import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.model.Status;


public interface IStatusService {
	public ResponseDTO addingStatusDetails(StatusDTO dto);
	public ResponseDTO getAllStatus();
	public ResponseDTO updateCandidateStatus(Long id,StatusDTO dto);
	public void deleteCandidaStatusd(Long id) ;


}
