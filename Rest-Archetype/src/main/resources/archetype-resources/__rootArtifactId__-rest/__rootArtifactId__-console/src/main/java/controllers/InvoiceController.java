#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import ${package}.exception.InvoiceAllreadyExistException;
import ${package}.exception.InvoiceNotFoundException;
import ${package}.model.Invoice;
import ${package}.service.InvoiceService;
import ${package}.exception.UserNotFoundException;
import ${package}.exception.InvalidInputException;

import static ${package}.constant.ErrorFault.INVOICE_ALREADY_EXIST;
import static ${package}.constant.ErrorFault.INVOICES_DO_NOT_EXIST;
import static ${package}.constant.ErrorFault.NO_INVOICE_FOUND;

@RestController
@RequestMapping("/invoices")
@Api(basePath = "/invoices", tags = "InvoiceController", description = "Controller API for Invoice functionalities")
public class InvoiceController {

	@Autowired
	private InvoiceService service;

	@PostMapping(value = "/invoice")
	@PreAuthorize("hasRole('ASSOCIATES') or hasRole('ADMINISTRATORS') ")
	@ApiOperation(value = "Creates a new Invoice.", notes = "Invoice Must not be empty")
	@ResponseBody
	public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice)
			throws InvalidInputException, InvoiceAllreadyExistException {

		try {
			service.createInvoice(invoice);

		} catch (InvoiceAllreadyExistException e) {
			throw new InvoiceAllreadyExistException(INVOICE_ALREADY_EXIST + ": " + invoice.getInvoiceId());
		}

		return new ResponseEntity<String>("Invoice: " + invoice.getInvoiceId() + " created successfully",
				HttpStatus.OK);

	}

	@GetMapping(value = "{userId}")
	@PreAuthorize("hasRole('ASSOCIATES') or hasRole('ADMINISTRATORS') ")
	@ApiOperation(value = "Retrieves a User's invoice.", notes = "Id is required.", response = Invoice.class)
	@ApiImplicitParam(name = "userId", required = true, dataType = "long", paramType = "path", value = "User's Primary Key")
	@ResponseBody
	public ResponseEntity<?> retrieveInvoiceByUserId(@PathVariable long userId)
			throws InvoiceNotFoundException, InvalidInputException, UserNotFoundException {

		Invoice response = null;
		try {
			response = service.getInvoiceByUserId(userId);
			response.add(linkTo(methodOn(UserManagementController.class).retrieveUser(response.getUserId()))
					.withRel("users"));

		} catch (InvoiceNotFoundException e) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + "For User: " + userId);
		}
		return new ResponseEntity<Invoice>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('ADMINISTRATORS') ")
	@ApiOperation(value = "Retrieves all User's invoices.", notes = "Admin Priveleges.", response = List.class)
	@ResponseBody
	public Collection<Invoice> retrieveAllInvoices()
			throws InvoiceNotFoundException, UserNotFoundException, InvalidInputException {

		List<Invoice> invoices = new ArrayList<Invoice>();
		try {
			invoices = (List<Invoice>) service.retrieveAllInvoices();
			for (Invoice invoice : invoices) {
				invoice.add(
						linkTo(methodOn(UserManagementController.class).retrieveUser(invoice.getUserId()))
								.withRel("users"));
			}

		} catch (InvoiceNotFoundException e) {
			throw new InvoiceNotFoundException(INVOICES_DO_NOT_EXIST);
		}

		return invoices;

	}

	@PutMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMINISTRATORS')")
	@ApiOperation(value = "Updates a Invoice record.")
	@ResponseBody
	public ResponseEntity<?> updateInvoice(@PathVariable long id, @RequestBody Invoice request)
			throws InvoiceNotFoundException, InvalidInputException {

		try {
			service.updateInvoice(id, request);

		} catch (InvoiceNotFoundException e) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + id);

		}
		return new ResponseEntity<String>("Invoice record: " + id + " updated successfully", HttpStatus.OK);

	}

	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMINISTRATORS') ")
	@ApiOperation(value = "Deletes a Invoice record by the specified id.", notes = "Id is required.")
	@ApiImplicitParam(name = "id", required = true, dataType = "long", paramType = "path", value = "Invoice's Primary Key")
	@ResponseBody
	public ResponseEntity<String> deleteInvoice(@PathVariable long id)
			throws InvoiceNotFoundException, InvalidInputException {

		try {
			service.deleteInvoice(id);

		} catch (InvoiceNotFoundException infe) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + id);
		}

		return new ResponseEntity<String>("Invoice's record: " + id + " deleted successfully", HttpStatus.OK);

	}
}
