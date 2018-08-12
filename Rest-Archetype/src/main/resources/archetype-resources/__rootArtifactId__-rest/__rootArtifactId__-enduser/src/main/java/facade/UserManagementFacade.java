#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.facade;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import ${package}.exception.InvalidInputException;
import ${package}.exception.InvoiceAllreadyExistException;
import ${package}.exception.InvoiceNotFoundException;
import ${package}.exception.UserAllreadyExistException;
import ${package}.exception.UserNotFoundException;
import ${package}.model.*;
import ${package}.service.InvoiceService;
import ${package}.service.UserManagementService;

public class UserManagementFacade {
	
	@Autowired
	private UserManagementService userService;

	@Autowired
	private InvoiceService invoiceService;
	
	public void createUser(User user) throws InvalidInputException, InvoiceAllreadyExistException, UserAllreadyExistException, UserNotFoundException{
		Invoice invoice = new Invoice();
		
		invoice.setInvoiceId(user.getUserId());
		invoice.setPrice(Math.random());
		invoice.setTitle("Invoice Fee");
	    invoice.setUserId(user.getUserId());
	    invoice.setFirstName(user.getFirstName());
	    invoice.setLastName(user.getLastName());
	    
	    userService.saveUser(user);
	    invoiceService.createInvoice(invoice);
		
	}
	
	public User retrieveUser1(long id){
		User response = new User();
		return response;
	}
	
	public User retrieveUser(long id){
		User response = new User();
		return response;
	}
	public Collection<User> retrieveAllUsers() throws UserNotFoundException{
		return  userService.retrieveAllUsers();
		
	}
	
	public void updateUser(User user) throws UserNotFoundException, InvalidInputException{
		userService.updateUser(user);
		
	}
	
	public void deleteUser(long id) throws UserNotFoundException, InvalidInputException, InvoiceNotFoundException{
		userService.deleteUser(id);
		invoiceService.deleteInvoice(id);
		
	}
	
	public void createInvoice(Invoice invoice) throws InvalidInputException, InvoiceAllreadyExistException{
		invoiceService.createInvoice(invoice);
		
	}
	
	public Invoice retrieveInvoice(long id) throws InvoiceNotFoundException, InvalidInputException{
		return invoiceService.getInvoiceByUserId(id);
	}
	
	public void deleteInvoice(long id) throws InvoiceNotFoundException, InvalidInputException{
		invoiceService.deleteInvoice(id);
	}

}
