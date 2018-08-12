#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ${package}.exception.InvalidInputException;
import ${package}.exception.InvoiceAllreadyExistException;
import ${package}.exception.InvoiceNotFoundException;
import ${package}.model.Invoice;


public class InvoiceDaoTest {

	public Invoice request = new Invoice();
	public Invoice response = new Invoice();
	public Invoice retrieveRequest = new Invoice();
	public Invoice update = new Invoice();

	public Collection<Invoice> invoices = new ArrayList<Invoice>();

	@Mock
	public InvoiceDao repoMock;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void testMockCreation() {
		assertNotNull(repoMock);

		request.setInvoiceId(1);
		request.setFirstName("test");
		request.setLastName("test");

		retrieveRequest.setInvoiceId(1);

		response.setInvoiceId(1);
		response.setFirstName("test");
		response.setLastName("test");

		update.setInvoiceId(1);
		update.setFirstName("test");
		update.setLastName("testing");

		invoices.add(request);
		invoices.add(update);

	}

	public void save() throws InvalidInputException, InvoiceAllreadyExistException {
		doNothing().when(repoMock).save(request);

	}

	public void retrieve() throws InvalidInputException, InvoiceNotFoundException {
		when(repoMock.retrieve((long) 1)).thenReturn(response);
		assertEquals(response, repoMock.retrieve((long) 1));

	}

	public void retrieveAllInvoices() throws InvoiceNotFoundException {
		when(repoMock.retrieveAllInvoices()).thenReturn(invoices);
		assertEquals(invoices, repoMock.retrieveAllInvoices());

	}

	public void delete() throws InvoiceNotFoundException, InvalidInputException {
		doNothing().when(repoMock).delete(1L);

	}

	@SuppressWarnings("unchecked")
	@Test(expected = InvalidInputException.class)
	public void testInputInvalidExceptionWhenRetrieve()
			throws InvalidInputException, InvoiceAllreadyExistException, InvoiceNotFoundException {
		
	      when(repoMock.retrieve((Long) null)).thenThrow(InvalidInputException.class);

		repoMock.retrieve(null);

	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = InvoiceNotFoundException.class)
	public void testInvoiceNotFoundExceptionWhenRetrieveAllInvoices()
			throws InvoiceNotFoundException {
		
	      when(repoMock.retrieveAllInvoices()).thenThrow(InvoiceNotFoundException.class);

		repoMock.retrieveAllInvoices();

	}
	

}
