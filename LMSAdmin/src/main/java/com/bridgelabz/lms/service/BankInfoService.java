package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.response.Response;
@Service
public interface BankInfoService {
	public Response addingBankDetails(String token,BankDto dto);
	public Response getAllBankDeatils(String token);
	public Response updateBankInfo(String token,Integer id,UpdateBankDto dto);
	public void deleteBankDetails(String token,Integer id) ;
}
//
//ResponseDto getAllCandidateBankInfo(String token);
//
//ResponseDto addBankDetail(String token, LMSHiringBankInfoDto lmsBankInfo) throws LMSExceptions;
//
//ResponseDto updateBankDetail(String token, int id, LMSHiringBankInfoDto lmsBankInfo) throws LMSExceptions;
//
//void deleteBankDetailById(String token, int id) throws LMSExceptions;
