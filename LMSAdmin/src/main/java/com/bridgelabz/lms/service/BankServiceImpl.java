package com.bridgelabz.lms.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.BankDto;
import com.bridgelabz.lms.dto.UpdateBankDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.BankInfo;
import com.bridgelabz.lms.repository.BankRepository;
import com.bridgelabz.lms.response.Response;
@Service
public class BankServiceImpl implements BankInfoService {

	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private BankRepository bankrepo;
	
	@Override
	public Response addingBankDetails(String token,BankDto dto) {
		BankInfo bankinfo=modelmapper.map(dto, BankInfo.class);
		System.out.println(bankinfo);
		bankrepo.save(bankinfo);
		return new Response("Added Status: ", bankinfo,201,"true");
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
			return new Response("regitration sucess", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
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
		
	}
