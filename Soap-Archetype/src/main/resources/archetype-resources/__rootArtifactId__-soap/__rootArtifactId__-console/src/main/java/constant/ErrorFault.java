#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constant;

import org.springframework.stereotype.Component;

@Component
public class ErrorFault {
	
	public static final String INVALID_INPUT = "Invalid Input";
	public static final String USER_ALREADY_EXIST = "User Already Exist";
	public static final String NO_USER_FOUND = "User Not Found";
	public static final String NO_USERS_FOUND = "Users Not Found";
	
}