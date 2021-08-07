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
import com.bridgelabz.lms.service.IStatusService;
import com.bridgelabz.lms.service.StatusServiceImpl;

@RestController
public class StatusController {
	
	@Autowired
	private IStatusService lmsStatusService;
	
	
	@PostMapping("/addstatusDetails/{token}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> addingStatusDetails(@PathVariable String token,@RequestBody StatusDTO dto,BindingResult result){
		Response respDTO = lmsStatusService.addingStatusDetails(token,dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/getstatus/{token}")
	public ResponseEntity<Response> getStatus(@PathVariable String token) {
		Response respDTO = lmsStatusService.getAllStatus(token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatestatus/{token}/{id}/{keyText}")
	public ResponseEntity<Response> updateCandidateStatus(@PathVariable String token,@RequestBody Long id ,String  keyText) {
		Response respDTO = lmsStatusService.updateCandidateStatus(token,id, keyText);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{token}/{id}")
	public void deleteCandidaStatusd(@PathVariable String token,@PathVariable Long id)  {
		lmsStatusService.deleteCandidaStatusd(token,id);
	}
	
	
}