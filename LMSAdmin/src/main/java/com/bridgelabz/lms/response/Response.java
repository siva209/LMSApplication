package com.bridgelabz.lms.response;
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
		public Response(String string, Integer id) {
			// TODO Auto-generated constructor stub
		}
		public Response(String message2) {
			// TODO Auto-generated constructor stub
		}
		public Response(String message2, int i, String string) {
			// TODO Auto-generated constructor stub
		}
}

