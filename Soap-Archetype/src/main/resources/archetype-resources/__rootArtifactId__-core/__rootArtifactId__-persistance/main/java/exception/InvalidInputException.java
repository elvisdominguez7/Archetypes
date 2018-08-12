package ${package}.exception;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@Component
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ResponseExceptionMessage responseMsg = null;
	
	public InvalidInputException() {}
   
	public InvalidInputException(String msg) {
	super(msg); 
	
	responseMsg = new ResponseExceptionMessage();
	
	responseMsg.setTitle(msg);
	responseMsg.setTimeStamp(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
	responseMsg.setMessage(msg);
	
	}
	
	public String getMessage() {
		return responseMsg.getMessage(); 
	}
}
