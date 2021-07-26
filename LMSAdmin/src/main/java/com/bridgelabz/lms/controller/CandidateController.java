package com.bridgelabz.lms.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.lms.dto.UpdateDto;
import com.bridgelabz.lms.dto.UserDTO;
import com.bridgelabz.lms.exception.InvalidDetailsException;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.CandidateServiceImpl;
import com.bridgelabz.lms.util.JwtUtil;


@RestController
public class CandidateController {
	@Autowired
	private CandidateServiceImpl userimpl;
	
	@Autowired
	private JwtUtil jwt;
	/**
	 * Register User : used to register the user
	 * @param dto
	 * @return register response
	 * @throws InvalidDetailsException 
	 */
	
	@PostMapping("/registeruser")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> registerUser(@RequestBody UserDTO dto,BindingResult result) throws InvalidDetailsException{
		Response response=userimpl.registerCandidate(dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@GetMapping("/verifyemail/{token}")
	public ResponseEntity<Response> verifyemail(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("email verified",userimpl.verify(token),201,"true"),HttpStatus.ACCEPTED);
	}
	@GetMapping("/getallHiredCandidates")
	public ResponseEntity<Response> getAllHiredcandidates() {
		Response respDTO = userimpl.getAllHiredcandidates();
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getuser")
	public ResponseEntity<Response> getuser(@RequestHeader String token)
	{
		 Long id=jwt.parseJWT(token);
	     Candidate user=userimpl.getUserById(token);
		return new ResponseEntity<Response>(new Response("welcome",userimpl.getUserById(token),200,"true"),HttpStatus.OK);
	}
	
	@GetMapping("/getuserbyprofile/{id}")
	public ResponseEntity<Response> getCandidateProfileById(@PathVariable Long id){
	{
		//System.out.println("Getting user with ID {}."+userId);
		return new ResponseEntity<Response>(new Response("welcome",userimpl.getCandidateProfileById(id),200,"true"),HttpStatus.OK);
	}
	}
	
	@PutMapping("/updatehiring/{id}")
	public ResponseEntity<Response> updateCandidate(@PathVariable Long id, @RequestBody UpdateDto dto,BindingResult result) {
		Response respDTO = userimpl.updateCandidate(id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}

	
	@DeleteMapping("/deletehiring/{id}")
	public void deleteCandidateHiringById(@PathVariable Long id)  {
		userimpl.deleteCandidateHiringById(id);
	}
	
}
