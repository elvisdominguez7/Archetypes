package ${package}.constant;

import org.springframework.stereotype.Component;

@Component
public class ErrorFault {
	
	public static final String INVALID_INPUT = "Wrong Input Data";
	public static final String NO_USER_FOUND = "User Not Found";
	public static final String USER_ALREADY_EXIST = "User Already Exist";
	public static final String USERS_DO_NOT_EXIST = "No Users Available In the Database";
	
	public static final String INVOICE_ALREADY_EXIST = "Invoice Already Exist";
	public static final String NO_INVOICE_FOUND = "No Invoice found";
	public static final String INVOICES_DO_NOT_EXIST = "No Invoices Available In The Database";
	
	private ErrorFault() {}
	
}
