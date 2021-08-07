package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.dto.UpdateQualificationDto;
import com.bridgelabz.lms.response.Response;
@Service
public interface IQualificationService {
	public Response addingQualificationDetails(String token,QualificationDto dto);
	public Response getAllQualificationDeatils(String token);
	public Response updateQualification(String token,Integer id,UpdateQualificationDto dto);
	public void deleteQualification(String token,Integer id) ;

}
