package ${package}.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ${package}.dao.InvoiceDao;
import ${package}.model.Invoice;



public class InvoiceServiceTest {
	
	 	@Mock
	    private InvoiceDao repoMock;
	    
	    private Invoice request = new Invoice();
	    private Invoice reTrievereq = new Invoice();
	    private Invoice response = new Invoice();
	    
	    
	    
	    @Before
	    public void setupMock() {
	       MockitoAnnotations.initMocks(this);
	       
	    }

	    @Test
	    public void testMockCreation(){
	        assertNotNull(repoMock);
	        
	        request.setInvoiceId(1L);
	        request.setFirstName("test");
	        request.setLastName("testing");
	        
	        reTrievereq.setInvoiceId(1L);
	       
	        
	        response.setInvoiceId(1L);
	        response.setFirstName("test");
	        response.setLastName("testing");
	    }

	    @Test
	    public void createInvoice() throws Exception {
	        
	        doNothing().when(repoMock).save(request);   
	    }

	    @Test
	    public void retrieveCustomer() throws Exception {
	        
	        when(repoMock.retrieve(1L)).thenReturn(response);
	        assertEquals(response, repoMock.retrieve(1L));
	        
	    }

	

	    @Test
	    public void deleteCustomer() throws Exception {
	        doNothing().when(repoMock).delete(1L);
	            
	        
	    }

}
