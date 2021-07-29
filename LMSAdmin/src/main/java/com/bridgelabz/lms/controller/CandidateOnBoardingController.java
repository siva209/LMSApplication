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
import com.bridgelabz.lms.service.CandidateOnBoardingServiceImpl;

@RestController
public class CandidateOnBoardingController {
	@Autowired
	private CandidateOnBoardingServiceImpl onboardingserviceimpl;
	
	
	@PostMapping("/OnBoarding")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response>createUser(String token,@RequestBody CandidatEOnBoardingDTO dto,BindingResult result){
		Response response=onboardingserviceimpl.createUser(token,dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	 
	
	@GetMapping("/getallOnboardingCandidates")
	public ResponseEntity<Response> getAllOnBoardingcandidates(String token) {
		Response respDTO = onboardingserviceimpl.getAllOnBoardingcandidates(token);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatecandidateOnBoard/{id}")
	public ResponseEntity<Response> updateOnBoardingCandidate(String token,@PathVariable Long id, @RequestBody CandidatEOnBoardUpdateDTO dto,BindingResult result) {
		Response respDTO = onboardingserviceimpl.updateOnBoardingCandidate(token, id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@PutMapping("/updatestatusonboardHiring/{id}/{keyText}")
	public ResponseEntity<Response> updatestatusHiring(String token, @PathVariable Long id,@PathVariable String keyText)  {                           
		Response respDTO = onboardingserviceimpl.updateStatus(token, id, keyText);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@DeleteMapping("/deleteonboardingcandidate/{id}")
	public ResponseEntity<Response> deleteOnBoardingCandidateById(String token,@PathVariable Long id) {
		onboardingserviceimpl.deleteOnBoardingCandidateById(token, id);
		Response respDTO = new Response("Deleted Contact with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
}