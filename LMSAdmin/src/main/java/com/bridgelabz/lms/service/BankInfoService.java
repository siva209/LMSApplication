package com.bridgelabz.lms.service;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.model.FileDB;
import com.bridgelabz.lms.response.Response;
@Service
public interface BankInfoService {
	public Response addingBankDetails(String token,BankDto dto);
	public Response getAllBankDeatils(String token);
	public Response updateBankInfo(String token,Integer id,UpdateBankDto dto);
	public void deleteBankDetails(String token,Integer id) ;
	public Response store(String token,int id,MultipartFile panFile,MultipartFile aaadharFile,MultipartFile passBookFile) throws Exception;
//	public FileDB store(MultipartFile file) throws IOException; 
//	public FileDB getFile(String id) ;
//	public Stream<FileDB> getAllFiles();
	 
}
