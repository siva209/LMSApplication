package com.bridgelabz.lms.service;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.model.BankInfo;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.model.QualificationInfo;
import com.bridgelabz.lms.model.Status;
import com.bridgelabz.lms.repository.BankRepository;
import com.bridgelabz.lms.repository.CandidateRepository;
import com.bridgelabz.lms.repository.QualificationRepository;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.util.Jms;
import com.bridgelabz.lms.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private BCryptPasswordEncoder pwdencoder;

	@Autowired
	private JwtUtil jwt;
	@Autowired
	private Jms jms;
	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private CandidateRepository candidaterrepo;
	@Autowired
	private BankRepository bankrepo;
	
	@Autowired
	private QualificationRepository qualificationrepo;
	
	
	@Override
	public Response registerCandidate(String token,CandidateHiredDTO dto) {

		Candidate AddDetails = modelmapper.map(dto, Candidate.class);
		System.out.println(AddDetails);
		candidaterrepo.save(AddDetails);
		return new Response("Added Status: ", AddDetails,201,"true");
	}
	
		
	@Override
	public Response getAllHiringCandidate(String token) {
		//int Id = tokenutil.decodeToken(token);
		List<Candidate> isUserPresent = candidaterrepo.findAll();
		return new Response("List of HiredCandidates are", isUserPresent, 200, "true");
	}

	@Override
	public Response getCandidate(String token, Long id) {
		// int Id = tokenutil.decodeToken(token);
				Optional<Candidate> isUserPresent = candidaterrepo.findById(id);
				Candidate candidates = isUserPresent.get();
				return new Response("List of HiredCandidates are", isUserPresent, 200, "true");
			}

	@Override
	public Candidate getCandidateProfileById(Long id) {
		return candidaterrepo.getCandidateProfileById(id)
		.orElseThrow(() -> new CandidateRegistrationException("user not exists", HttpStatus.OK, id, "false"));
}


	@Override
	public Response updateCandidate(Long id, UpdateHiringDto dto) {
		Optional<Candidate> isUserPresent = candidaterrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setFirstName(dto.getFirstName());
			isUserPresent.get().setMiddleName(dto.getMiddleName());
			isUserPresent.get().setLastName(dto.getLastName());
			isUserPresent.get().setEmail(dto.getEmail());
			isUserPresent.get().setMobileNum(dto.getMobileNum());
			isUserPresent.get().setHiredcity(dto.getHiredcity());
			isUserPresent.get().setParentName(dto.getParentName());
			isUserPresent.get().setParentMobile(dto.getParentMobile());
			isUserPresent.get().setTemporaryAddress(dto.getTemporaryAddress());
			isUserPresent.get().setParentOccupation(dto.getParentOccupation());
			isUserPresent.get().setParentAnnualSalary(dto.getParentAnnualSalary());
			isUserPresent.get().setPermanentAddress(dto.getPermanentAddress());
			isUserPresent.get().setProfileImage(dto.getProfileImage());
			isUserPresent.get().setFolderId(dto.getFolderId());
			isUserPresent.get().setCreatorStamp(dto.getCreatorStamp());
			isUserPresent.get().setStatus(dto.getStatus());
			isUserPresent.get().setUpdateStamp(LocalDateTime.now());
			
			//isUserPresent.get().setHiringBankInfo(lmsCandidateHiring.getBank_Id());
			//isUserPresent.get().setQualificationInfo(lmsCandidateHiring.getQualificationInfo());
			System.out.println(isUserPresent);
			candidaterrepo.save(isUserPresent.get());
			return new Response("regitration sucess", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}


	@Override
	public void deleteCandidateHiringById(String token,Long id) {
		// int Id = tokenutil.decodeToken(token);
		Optional<Candidate> isUserPresent = candidaterrepo.findById(id);
		if (isUserPresent.isPresent()) {
			candidaterrepo.deleteById(id);
		}else {
			 new CandidateRegistrationException("Candidate to be Delete Not found",null,404,"true");
		}
	}
	
	@Override
	public Response updateHiringStatus(String token, Long id, String keyText) {
		Optional<Candidate> isUserPresent = candidaterrepo.findById(id);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setStatus(keyText);
			candidaterrepo.save(isUserPresent.get());
			return new Response("Candidate status updated Successfully ", isUserPresent, 201, "true");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}


	@Override
	public Response jobOfferNotificationMail(String token, String email) {
		// int Id = tokenutil.decodeToken(token);
				Optional<Candidate> isUserPresent = candidaterrepo.findAllByemail(email);
				boolean emailmatch = isUserPresent.get().getEmail().matches(email);
				if (emailmatch == true) {
					// String token = tokenutil.createToken(createUser.getId());
					System.out.println(token);
					String body = "This is a Candidate Job Offer Notification";
					System.out.println(body);
					jms.sendEmail(isUserPresent.get().getEmail(), "Job Offer", body);
					return new Response("Successfully send notification ", isUserPresent, 201, "true");
				} else {
					throw new CandidateRegistrationException("invalid details", null, 400, "true");
				}
			}


	@Override
	public Response getCount(String token) {
		// int Id = tokenutil.decodeToken(token);
				List<Candidate> isUserPresent = candidaterrepo.findAll();
				long count = 0;
				for (Iterator iterator = isUserPresent.iterator(); iterator.hasNext();) {
					Candidate lmsHiring = (Candidate) iterator.next();
					count++;
				}
				return new Response("Number of Candidates : ", count,201,"true");
			}
		}
	





