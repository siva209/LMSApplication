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
import com.bridgelabz.lms.dto.CandidatEOnBoardUpdateDTO;
import com.bridgelabz.lms.dto.CandidatEOnBoardingDTO;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.ICandidateOnBoardingService;
import com.bridgelabz.lms.service.CandidateOnBoardingServiceImpl;

@RestController
public class CandidateOnBoardingController {
	
	@Autowired
	private ICandidateOnBoardingService candidateOnBoardservice;
	
	
	@PostMapping("/OnBoarding/{token}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response>createUser(@PathVariable String token,@RequestBody CandidatEOnBoardingDTO dto,BindingResult result){
		Response response=candidateOnBoardservice.createUser(token,dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	 
	
	@GetMapping("/getallOnboardingCandidates/{token}")
	public ResponseEntity<Response> getAllOnBoardingcandidates(@PathVariable String token) {
		Response respDTO = candidateOnBoardservice.getAllOnBoardingcandidates(token);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatecandidateOnBoard/{token}/{id}")
	public ResponseEntity<Response> updateOnBoardingCandidate(@PathVariable  String token,@PathVariable Long id, @RequestBody CandidatEOnBoardUpdateDTO dto,BindingResult result) {
		Response respDTO = candidateOnBoardservice.updateOnBoardingCandidate(token, id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@PutMapping("/updatestatusonboardHiring/{token}/{id}/{keyText}")
	public ResponseEntity<Response> updatestatusHiring(@PathVariable  String token, @PathVariable Long id,@PathVariable String keyText)  {                           
		Response respDTO = candidateOnBoardservice.updateStatus(token, id, keyText);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@DeleteMapping("/deleteonboardingcandidate/{token}/{id}")
	public ResponseEntity<Response> deleteOnBoardingCandidateById(@PathVariable String token,@PathVariable Long id) {
		candidateOnBoardservice.deleteOnBoardingCandidateById(token, id);
		Response respDTO = new Response("Deleted Contact with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	

	@GetMapping("/getonboardcount/{token}")
	public ResponseEntity<Response> getcount(@PathVariable String token) {
		Response respDTO = candidateOnBoardservice.getCount(token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
}