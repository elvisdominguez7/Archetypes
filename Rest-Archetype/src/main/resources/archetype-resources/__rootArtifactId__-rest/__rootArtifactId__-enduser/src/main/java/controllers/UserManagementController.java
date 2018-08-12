#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import static ${package}.constant.ErrorFault.NO_USER_FOUND;
import static ${package}.constant.ErrorFault.USERS_DO_NOT_EXIST;
import static ${package}.constant.ErrorFault.NO_INVOICE_FOUND;

import ${package}.exception.UserAllreadyExistException;
import ${package}.exception.UserNotFoundException;
import ${package}.facade.UserManagementFacade;
import ${package}.model.User;
import ${package}.exception.InvalidInputException;
import ${package}.exception.InvoiceAllreadyExistException;
import ${package}.exception.InvoiceNotFoundException;






@RestController
@RequestMapping("/users")
@Api(basePath = "/users", tags = "UserManagementService", description = "Controller API for records functionalities")
public class UserManagementController {

	@Autowired
	private UserManagementFacade facade;
	

	@PostMapping(value = "/user")
	@PreAuthorize("hasRole('ASSOCIATES') or hasRole('ADMINISTRTORS') ")
	@ApiOperation(value = "Creates a new User Record.", notes = "Remove UserId it will be Auto generated from DB")
	@ResponseBody
	public ResponseEntity<?> createUser(@RequestBody User request)
			throws InvalidInputException, UserAllreadyExistException, UserNotFoundException,
			InvoiceNotFoundException, InvoiceAllreadyExistException {
		
			User currentUser = facade.retrieveUser(request.getUserId());
			
			 Link custLink = linkTo(UserManagementController.class).slash(currentUser.getUserId()).withSelfRel();
			 Link invoiceLink = linkTo(InvoiceController.class).slash(currentUser.getUserId()).withRel("invoices");
			 currentUser.add(custLink);
			 currentUser.add(invoiceLink);

		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	

	@SuppressWarnings("null")
	public User retrieveUserHelper(long id)
			throws UserNotFoundException, InvalidInputException {

		return facade.retrieveUser(id);
	

	}

	@GetMapping(value = "{id}")
	@PreAuthorize("hasRole('ASSOCIATES') or hasRole('ADMINISTRTORS') ")
	@ApiOperation(value = "Retrieves a User Record by the specified id.", notes = "Id is required.", response = User.class)
	@ApiImplicitParam(name = "id", required = true, dataType = "long", paramType = "path", value = "User's Primary Key")
	@ResponseBody
	public ResponseEntity<?> retrieveUser(@PathVariable long id)
			throws UserNotFoundException, InvalidInputException, InvoiceNotFoundException {

		User response = null;
		response = facade.retrieveUser(id);
		try {
			response.add(linkTo(methodOn(InvoiceController.class).retrieveInvoiceByUserId(response.getUserId()))
					.withRel("invoices"));
		} catch (InvoiceNotFoundException e) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + response.getUserId());
			
		}

		return new ResponseEntity<User>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('ADMINISTRTORS') ")
	@ApiOperation(value = "Retrieves all Users.", notes = "Admin Priveleges.", response = List.class)
	@ResponseBody
	public Collection<User> retrieveAllUsers() throws UserNotFoundException{

		List<User> users = new ArrayList<User>();
		try {
			users = (List<User>) facade.retrieveAllUsers();
			if(users.isEmpty()) {
				throw new UserNotFoundException(USERS_DO_NOT_EXIST);
			}
			for(User user: users) {
				user.add(linkTo(methodOn(InvoiceController.class).retrieveInvoiceByUserId(user.getUserId())).withRel("invoices"));
			}
		} catch (UserNotFoundException | InvoiceNotFoundException | InvalidInputException e) {
			throw new UserNotFoundException(USERS_DO_NOT_EXIST);
		}

		return users;

	}

	@PutMapping(value = "/user")
	@PreAuthorize("hasRole('ADMINISTRTORS')")
	@ApiOperation(value = "Updates a User record.")
	@ResponseBody
	public ResponseEntity<?> updateUser(@RequestBody User request)
			throws UserNotFoundException, InvalidInputException, InvoiceNotFoundException {

			facade.updateUser(request);
			
			 Link custLink = linkTo(UserManagementController.class).slash(request.getUserId()).withSelfRel();
			 Link invoiceLink = linkTo(InvoiceController.class).slash(request.getUserId()).withRel("invoices");
			 request.add(custLink);
			 request.add(invoiceLink);
	
		return new ResponseEntity<User>(request, HttpStatus.OK);

	}

	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMINISTRTORS') ")
	@ApiOperation(value = "Deletes a User record by the specified id.", notes = "Id is required.")
	@ApiImplicitParam(name = "id", required = true, dataType = "long", paramType = "path", value = "User's Primary Key")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@PathVariable long id)
			throws UserNotFoundException, InvalidInputException, InvoiceNotFoundException {

		try {
			facade.deleteUser(id);
			

		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(NO_USER_FOUND + ": " + id);
		}


		return new ResponseEntity<String>("User's record: " + id + " deleted successfully", HttpStatus.OK);

	}

}
