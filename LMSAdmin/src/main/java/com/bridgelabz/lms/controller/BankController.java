package com.bridgelabz.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.model.FileDB;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.response.ResponseFile;
import com.bridgelabz.lms.response.ResponseMessage;
import com.bridgelabz.lms.service.IBankInfoService;
import com.bridgelabz.lms.service.BankServiceImpl;

import io.swagger.annotations.ApiOperation;
@RestController
public class BankController {
	
	@Autowired
	private IBankInfoService bankService;
	

	@PostMapping("/addbankDetails/{token}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> addingBankDetails(@PathVariable  String token,@RequestBody BankDto dto,BindingResult result){
		Response respDTO = bankService.addingBankDetails(token,dto);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getallbankDetails/{token}")
	public ResponseEntity<Response> getStatus(@PathVariable  String token) {
		Response respDTO = bankService.getAllBankDeatils(token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatebankdetails/{token}/{id}")
	public ResponseEntity<Response> updateBankInfo(@PathVariable  String token,@PathVariable Integer id, @RequestBody UpdateBankDto dto,BindingResult result) {
		Response respDTO = bankService.updateBankInfo(token,id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebankdetails/{token}/{id}")
	public ResponseEntity<Response> deleteBankDetails(@PathVariable  String token,@PathVariable Integer id) {
		bankService.deleteBankDetails(token,id);
		Response respDTO = new Response("Deleted Contact with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	

	@PostMapping(value = "/upload/{id}", consumes = { "multipart/form-data" })
	@ApiOperation(value = "Upload Documents", response = Response.class)
	public ResponseEntity<Response> addBankDetail(String token, @PathVariable int id,
			@RequestParam("panFile") MultipartFile panFile, @RequestParam("AadharFile") MultipartFile AadharFile,
			@RequestParam("passBookFile") MultipartFile passBookFile) throws  Exception {
		Response respDTO = bankService.store(token, id, panFile, AadharFile, passBookFile);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	  
	}


	
