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
import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.dto.UpdateHiringDto;
import com.bridgelabz.lms.response.Response;
import com.bridgelabz.lms.service.CandidateServiceImpl;
import com.bridgelabz.lms.util.JwtUtil;


@RestController
public class CandidateController {
	@Autowired
	private CandidateServiceImpl userimpl;

	/**
	 * Register User : used to register the user
	 * @param dto
	 * @return register response
	 * @throws InvalidDetailsException 
	 */
	
	@PostMapping("/registeruser")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response> registerUser(String token,@RequestBody CandidateHiredDTO dto,BindingResult result) {
		Response response=userimpl.registerCandidate(token, dto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getallHiredCandidatesss")
	public ResponseEntity<Response> getAllHiredcandidates(String token) {
		Response respDTO = userimpl.getAllHiringCandidate(token);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/getcandidatehiring/{id}")
	public ResponseEntity<Response> getCandidate(String token, @PathVariable Long id) {
		Response respDTO = userimpl.getCandidate(token, id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@GetMapping("/getuserbyprofile/{id}")
	public ResponseEntity<Response> getCandidateProfileById(@PathVariable Long id){
	{
		System.out.println("Getting user with ID {}."+id);
		return new ResponseEntity<Response>(new Response("welcome",userimpl.getCandidateProfileById(id),200,"true"),HttpStatus.OK);
	}
	}
	
	@PutMapping("/updatehiring/{id}")
	public ResponseEntity<Response> updateCandidate(@PathVariable Long id, @RequestBody UpdateHiringDto dto,BindingResult result) {
		Response respDTO = userimpl.updateCandidate(id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@PutMapping("/updatestatusHiring/{id}/{keyText}")
	public ResponseEntity<Response> updatestatusHiring(String token, @PathVariable Long id,@PathVariable String keyText)  {                           
		Response respDTO = userimpl.updateHiringStatus(token, id, token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletehiring/{id}")
	public ResponseEntity<Response> deleteCandidateHiringById(String token,@PathVariable Long id) {
		userimpl.deleteCandidateHiringById(token,id);
		Response respDTO = new Response("Deleted Contact with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	

	@PostMapping("/offerMail")
	public ResponseEntity<Response> jobOfferNotificationMail(String token, @RequestBody String email){
		Response respDTO = userimpl.jobOfferNotificationMail(token, email);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
}


