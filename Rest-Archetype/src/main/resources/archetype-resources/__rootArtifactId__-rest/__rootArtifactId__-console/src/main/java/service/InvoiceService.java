#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ${package}.dao.InvoiceDao;
import ${package}.exception.InvoiceNotFoundException;
import ${package}.model.Invoice;
import ${package}.exception.InvalidInputException;
import ${package}.exception.InvoiceAllreadyExistException;
import static ${package}.constant.ErrorFault.INVALID_INPUT;
import static ${package}.constant.ErrorFault.INVOICE_ALREADY_EXIST;
import static ${package}.constant.ErrorFault.NO_USER_FOUND;
import static ${package}.constant.ErrorFault.NO_INVOICE_FOUND;



@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceDao dao;

    
    public void createInvoice(Invoice invoice)throws InvalidInputException, InvoiceAllreadyExistException {
    	if(invoice == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + invoice);
		}
    	try {
    	dao.save(invoice);
    	} catch (DataAccessException dae) {
			throw new InvoiceAllreadyExistException(INVOICE_ALREADY_EXIST + ": " + invoice.getInvoiceId());
			
		}
    }
    
    
   
    public Invoice getInvoiceByUserId(long userId) throws InvoiceNotFoundException, InvalidInputException {
    		Invoice response ;
		
		if(Long.valueOf(userId) == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + userId);
		}
		
		try {
			response = dao.retrieve(userId);
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + userId);
			
		}

		return response;
    }
    public Collection<Invoice> retrieveAllInvoices() throws InvoiceNotFoundException{
		List<Invoice> invoices = new ArrayList<Invoice>() ;
		
		try {
			invoices = (List<Invoice>) dao.retrieveAllInvoices();
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND);
			
		}

		return invoices;

	}
	public void updateInvoice(long id, Invoice request) throws InvoiceNotFoundException, InvalidInputException{
		
		if(Long.valueOf(id) == null || request == null) {
			throw new InvalidInputException(INVALID_INPUT + id);
		}
		
		Invoice currentInvoice = null;
		try {
			 currentInvoice = dao.retrieve(id);
			 currentInvoice.setFirstName(request.getFirstName());
			 currentInvoice.setLastName(request.getLastName());
			 
			 dao.update(currentInvoice);

		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(NO_USER_FOUND + ": " + request.getInvoiceId());
		}

	}
	public void deleteInvoice(long id) throws InvoiceNotFoundException, InvalidInputException{
		
		Invoice currentId = null;
		
		if(Long.valueOf(id) == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + id);
		}
		try {
			currentId = dao.retrieve(id);
			
			if(currentId.getUserId()!=id) {
				throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + id);
				
			}
			dao.delete(id);
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + id);
		}
		
	}

  


}
