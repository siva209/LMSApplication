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
import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.BankServiceImpl;
@RestController
public class BankController {
	
	@Autowired
	private BankServiceImpl baknservice;
	

	@PostMapping("/addbankDetails")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> addingBankDetails(String token,@RequestBody BankDto dto,BindingResult result){
		Response respDTO = baknservice.addingBankDetails(token,dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getallbankDetails")
	public ResponseEntity<Response> getStatus(String token) {
		Response respDTO = baknservice.getAllBankDeatils(token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatebankdetails/{id}")
	public ResponseEntity<Response> updateBankInfo(String token,@PathVariable Integer id, @RequestBody UpdateBankDto dto,BindingResult result) {
		Response respDTO = baknservice.updateBankInfo(token,id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebankdetails/{id}")
	public ResponseEntity<Response> deleteBankDetails(String token,@PathVariable Integer id) {
		baknservice.deleteBankDetails(token,id);
		Response respDTO = new Response("Deleted Contact with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
}

	
