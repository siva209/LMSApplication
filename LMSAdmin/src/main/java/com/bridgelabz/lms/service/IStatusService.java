package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.dto.UpdateStatusDto;
import com.bridgelabz.lms.model.Status;
import com.bridgelabz.lms.response.Response;

@Service
public interface IStatusService {
	public Response addingStatusDetails(StatusDTO dto);
	public Response getAllStatus();
//	public Status verify(String token);
//	public Response updateCandidateStatus(Long id,UpdateStatusDto dto);
//	public void deleteCandidaStatusd(Long id) ;
}
