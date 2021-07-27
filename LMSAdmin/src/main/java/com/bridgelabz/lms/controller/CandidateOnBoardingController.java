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
	public ResponseEntity<Response>createUser(@RequestBody CandidatEOnBoardingDTO dto,BindingResult result){
		Response response=onboardingserviceimpl.createUser(dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	 
	
	@GetMapping("/getallOnboardingCandidates")
	public ResponseEntity<Response> getAllOnBoardingcandidates() {
		Response respDTO = onboardingserviceimpl.getAllOnBoardingcandidates();
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatecandidateOnBoard/{id}")
	public ResponseEntity<Response> updateOnBoardingCandidate(@PathVariable Long id, @RequestBody CandidatEOnBoardUpdateDTO dto,BindingResult result) {
		Response respDTO = onboardingserviceimpl.updateOnBoardingCandidate(id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteonboardingcandidate/{id}")
	public ResponseEntity<Response> deleteOnBoardingCandidateById(@PathVariable Long id) {
		onboardingserviceimpl.deleteOnBoardingCandidateById(id);
		Response respDTO = new Response("Deleted Contact with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
}