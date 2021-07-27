package com.bridgelabz.lms.response;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.bridgelabz.lms.model.Candidate;
//import com.bridgelabz.lms.model.CandidateOnboardingDetails;
import com.bridgelabz.lms.model.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
	
	String message;
	int status;
	Object data;
	String statusMsg;
		public Response(String message,Object user,int status,String statusMsg) 
		{
		this.message=message;
		this.status=status;
		this.data=user;
		this.statusMsg=statusMsg;
		}
		public Response(String string, Long id) {
			// TODO Auto-generated constructor stub
		}
		public Response(String string, Status addDetails) {
			// TODO Auto-generated constructor stub
		}
}
//		public Response(int i, String string, Optional<Candidate> isUserPresent) {
//			// TODO Auto-generated constructor stub
//		}
//		public Response(String string, int i, String string2) {
//			// TODO Auto-generated constructor stub
//		}
////		public Response(String string, List<Candidate> isUserPresent, int i, boolean b, HttpStatus ok) {
////			// TODO Auto-generated constructor stub
////		}
//}
//		
////		public Response(String string, Optional<Candidate> isUserPresent) {
////			// TODO Auto-generated constructor stub
////		}
////		public Response(String string, Optional<CandidateOnboardingDetails> isUserPresent) {
////			// TODO Auto-generated constructor stub
////		}
////}

