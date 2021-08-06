package com.bridgelabz.lms.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.BankInfo;
import com.bridgelabz.lms.model.FileDB;
import com.bridgelabz.lms.repository.BankRepository;
import com.bridgelabz.lms.response.Response;
@Service
public class BankServiceImpl implements IBankInfoService {
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private BankRepository bankrepo;
	
	@Override
	public Response addingBankDetails(String token,BankDto dto) {
		BankInfo bankinfo=modelmapper.map(dto, BankInfo.class);
		System.out.println(bankinfo);
		bankrepo.save(bankinfo);
		return new Response("Added Status Details: ", bankinfo,201,"true");
	}

	@Override
	public Response getAllBankDeatils(String token) {
		// int Id = tokenutil.decodeToken(token);
		List<BankInfo> isPresent = bankrepo.findAll();
		System.out.println(isPresent);
		return new Response("List of all candidates Bank Details  are",isPresent,200,"true");
	}

	@Override
	public Response updateBankInfo(String token,Integer id, UpdateBankDto dto) {
		// int Id = tokenutil.decodeToken(token);
		Optional<BankInfo> isUserPresent = bankrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setAadharNumber(dto.getAadharNumber());
			isUserPresent.get().setAadharPath(dto.getAadharPath());
			isUserPresent.get().setBankAccountNumber(dto.getBankAccountNumber());
			isUserPresent.get().setBankName(dto.getBankName());
			isUserPresent.get().setCreatorStamp(dto.getCreatorStamp());
			isUserPresent.get().setIfscCode(dto.getIfscCode());
			isUserPresent.get().setPanNumber(dto.getPanNumber());
			isUserPresent.get().setPanPath(dto.getPanPath());
			isUserPresent.get().setPassbookPath(dto.getPassbookPath());
			isUserPresent.get().setUpdateStamp(dto.getUpdateStamp().now());
			System.out.println(isUserPresent);
			bankrepo.save(isUserPresent.get());
			return new Response("Update bank details  successfully", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid bank details", null, 400, "true");
		}
	}

	@Override
	public void deleteBankDetails(String token,Integer id) {
		// int Id = tokenutil.decodeToken(token);
		Optional<BankInfo> isUserPresent = bankrepo.findById(id);
		if (isUserPresent.isPresent()) {
			bankrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate Bank Details to be Delete Not found",null,404,"true");
		}
	}

	@Override
	public Response store(String token, int id, MultipartFile panFile, MultipartFile aadharFile,
			MultipartFile passBookFile) throws Exception {
		Optional<BankInfo> isUserPresent = bankrepo.findById(id);
		if (isUserPresent.isPresent()) {

			String pan = StringUtils.cleanPath(panFile.getOriginalFilename());
			String Aadhar = StringUtils.cleanPath(aadharFile.getOriginalFilename());
			String passBook = StringUtils.cleanPath(passBookFile.getOriginalFilename());

			isUserPresent.get().setPanPath(pan);
			isUserPresent.get().setAadharPath(Aadhar);
			isUserPresent.get().setPassbookPath(passBook);

			bankrepo.save(isUserPresent.get());
			
			return new Response("Successfully Uploading Multiple Files  ", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}

//	@Override
//	public FileDB getFile(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Stream<FileDB> getAllFiles() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
		
	}

	