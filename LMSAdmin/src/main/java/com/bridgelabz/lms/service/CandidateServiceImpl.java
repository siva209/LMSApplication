package com.bridgelabz.lms.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.lms.dto.UpdateDto;
import com.bridgelabz.lms.dto.UserDTO;
import com.bridgelabz.lms.exception.CandidateRegistrationException;
import com.bridgelabz.lms.exception.InvalidDetailsException;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.repository.CandidateRepository;
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
	private JwtUtil jwt = new JwtUtil();
	@Autowired
	private Jms jms;
	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private CandidateRepository userrepo;

	@Override
	public Response registerCandidate(UserDTO dto) throws InvalidDetailsException {
		Optional<Candidate> isuserprsent = userrepo.isEmailExists(dto.getEmail());
		if (isuserprsent.isPresent()) {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		} else {
			Candidate user = modelmapper.map(dto, Candidate.class);
			user.setCreatorStamp(LocalDateTime.now());
			user.setUpdateStamp(LocalDateTime.now());
			userrepo.save(user);
			log.info(user.getFirstName() + " registered " + "date:" + user.getCreatorStamp());
			String body = "http://localhost:8080/verifyemail/" + jwt.jwtToken(user.getId());
			System.out.println(body);
			jms.sendEmail(user.getEmail(), "verification email", body);
			return new Response("regitration sucess", user, 201, "true");
		}

	}

	@Override
	public Candidate verify(String token) {
		long id = jwt.parseJWT(token);
		Candidate user = userrepo.isIdExists(id)
				.orElseThrow(() -> new CandidateRegistrationException("user not exists", HttpStatus.OK, id, "false"));
		user.setVerifyEmail(true);
		userrepo.save(user);
		return user;
	}

	@Override
	public Response getAllHiredcandidates() {
		List<Candidate> isUserPresent = userrepo.findAll();
		return new Response("List of HiredCandidates are", isUserPresent, 200, "true");
	}

	public Candidate getUserById(String token) {
		long id = jwt.parseJWT(token);
		return userrepo.getUserById(id)
				.orElseThrow(() -> new CandidateRegistrationException("user not exists", HttpStatus.OK, id, "false"));
	}

	@Override
	public Candidate getCandidateProfileById(Long id) {
		return userrepo.getCandidateProfileById(id)
				.orElseThrow(() -> new CandidateRegistrationException("user not exists", HttpStatus.OK, id, "false"));
	}

	@Override
	public Response updateCandidate(Long id, UpdateDto dto) {
		// int Id = tokenutil.decodeToken(token);
		Optional<Candidate> isUserPresent = userrepo.findById(id);
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
			// isUserPresent.get().setStatus(lmsCandidateHiring.getStatus());
			isUserPresent.get().setUpdateStamp(LocalDateTime.now());
			System.out.println(isUserPresent);
			userrepo.save(isUserPresent.get());
			return new Response("Candidate Added:", 200, "isUserPresent");
		} else {
			throw new CandidateRegistrationException("invalid details", null, 400, "true");
		}
	}

	@Override
	public void deleteCandidateHiringById(Long id) {
		userrepo.deleteById(id);
	}

}
