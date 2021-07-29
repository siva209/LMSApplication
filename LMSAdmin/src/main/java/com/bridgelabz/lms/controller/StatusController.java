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
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.StatusServiceImpl;

@RestController
public class StatusController {
	
	@Autowired
	private StatusServiceImpl lmsStatusService;
	
	
	@PostMapping("/addstatusDetails")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> addingStatusDetails(@RequestBody StatusDTO dto,BindingResult result){
		Response respDTO = lmsStatusService.addingStatusDetails(dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/getstatus")
	public ResponseEntity<Response> getStatus() {
		Response respDTO = lmsStatusService.getAllStatus();
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatestatus/{id}/{keyText}")
	public ResponseEntity<Response> updateCandidateStatus(@RequestBody Long id ,String  keyText) {
		Response respDTO = lmsStatusService.updateCandidateStatus(id, keyText);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCandidaStatusd(@PathVariable Long id)  {
		lmsStatusService.deleteCandidaStatusd(id);
	}
	
	
}