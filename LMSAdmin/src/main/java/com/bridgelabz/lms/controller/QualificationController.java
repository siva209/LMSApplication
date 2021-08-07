package com.bridgelabz.lms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.dto.UpdateQualificationDto;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.IQualificationService;
import com.bridgelabz.lms.service.QualificationServiceImpl;
@RestController
public class QualificationController {
	@Autowired
	private IQualificationService qualificationservice;
	

	@PostMapping("/addQualificationDetails/{token}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> addingQualificationDetails(@PathVariable String token,  @RequestBody QualificationDto dto,BindingResult result){
		Response respDTO = qualificationservice.addingQualificationDetails(token,dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getallqualificationDetails/{token}")
	public ResponseEntity<Response> getAllQualificationDeatils(@PathVariable String token) {
		Response respDTO = qualificationservice.getAllQualificationDeatils(token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updateQualificationdetails/{token}/{id}")
	public ResponseEntity<Response> updateQualification(@PathVariable String token,  @PathVariable Integer id, @RequestBody UpdateQualificationDto dto,BindingResult result) {
		Response respDTO = qualificationservice.updateQualification(token,id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@DeleteMapping("/deletequalification/{token}/{id}")
    public ResponseEntity<Response> deleteQualification(@PathVariable String token,  @PathVariable Integer id) {
	qualificationservice.deleteQualification(token,id);
	Response respDTO = new Response("Deleted Contact with id : ", id);
	return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
}
}