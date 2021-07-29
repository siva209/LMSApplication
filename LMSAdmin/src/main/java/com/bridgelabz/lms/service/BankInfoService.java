package com.bridgelabz.lms.service;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.response.Response;
@Service
public interface BankInfoService {
	public Response addingBankDetails(BankDto dto);
	public Response getAllBankDeatils();
	public Response updateBankInfo(Integer id,UpdateBankDto dto);
	public void deleteBankDetails(Integer id) ;

}
