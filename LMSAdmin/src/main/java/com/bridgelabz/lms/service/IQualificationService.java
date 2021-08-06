package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.dto.UpdateQualificationDto;
import com.bridgelabz.lms.response.Response;
@Service
public interface IQualificationService {
	public Response addingQualificationDetails(QualificationDto dto);
	public Response getAllQualificationDeatils();
	public Response updateQualification(Integer id,UpdateQualificationDto dto);
	public void deleteQualification(Integer id) ;

}
