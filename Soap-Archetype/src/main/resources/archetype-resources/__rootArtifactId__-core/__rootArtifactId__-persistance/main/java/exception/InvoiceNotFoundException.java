package ${package}.exception;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import ${package}.exception.ResponseExceptionMessage;


@Component
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvoiceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	private ResponseExceptionMessage responseMsg = null;
	
	public  InvoiceNotFoundException() {}
	
    public InvoiceNotFoundException(String msg) {
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
