package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.response.Response;

@Service
public interface IStatusService {
	public Response addingStatusDetails(String token,StatusDTO dto);
	public Response getAllStatus(String token);
	public Response updateCandidateStatus(String token,Long id,String  keyText);
	public void deleteCandidaStatusd(String token,Long id) ;
}
