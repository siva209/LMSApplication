package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.response.Response;

@Service
public interface IStatusService {
	public Response addingStatusDetails(StatusDTO dto);
	public Response getAllStatus();
	public Response updateCandidateStatus(Long id,String  keyText);
	public void deleteCandidaStatusd(Long id) ;
}
