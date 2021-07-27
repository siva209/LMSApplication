package com.bridgelabz.lms.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.dto.StatusDTO;
import com.bridgelabz.lms.dto.UpdateStatusDto;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.IStatusService;

@RestController
public class StatusController {
	@Autowired
	private IStatusService lmsStatusService;
	
	
	@PostMapping("/addstatusDetails/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> addingStatusDetails(@RequestBody StatusDTO dto){
		Response respDTO = lmsStatusService.addingStatusDetails(dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getstatus")
	public ResponseEntity<Response> getStatus() {
		Response respDTO = lmsStatusService.getAllStatus();
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("//updatestatus/{id}")
	public ResponseEntity<Response> updateCandidateStatus(@RequestBody Long id ,UpdateStatusDto dto) {
		Response respDTO = lmsStatusService.updateCandidateStatus(id, dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletehiring/{id}")
	public void deleteCandidaStatusd(@PathVariable Long id)  {
		lmsStatusService.deleteCandidaStatusd(id);
	}
	
	
}