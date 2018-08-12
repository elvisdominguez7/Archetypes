#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;


@Component
public class ResponseExceptionMessage {
	
	private String timeStamp;
	private String title;
    private String message;
    
    public ResponseExceptionMessage(String msg){
        this.message = msg;
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
 
    }
    

	public ResponseExceptionMessage() {
		
	}


	public void setTimeStamp(String format) {
		this.timeStamp = format;
		
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

 
     
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }


}

