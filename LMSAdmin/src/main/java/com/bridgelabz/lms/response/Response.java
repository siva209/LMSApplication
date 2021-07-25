package com.bridgelabz.lms.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.bridgelabz.lms.model.Candidate;

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
		public Response(String string, List<Candidate> isUserPresent, int i, boolean b, HttpStatus ok) {
			// TODO Auto-generated constructor stub
		}
}
