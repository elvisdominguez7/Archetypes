#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.hateoas.ResourceSupport;


public class InvoiceTest extends ResourceSupport {
	
	 private static final double PRECISION = 0.00001;
	private Invoice createReq = new Invoice();
	
    
	@Test
	public void setInvoiceIdTest() {
		createReq.setInvoiceId(1L);
		assertEquals(1L, createReq.getInvoiceId());

	}
	@Test
	public void getInvoiceIdTest() {
		createReq.setInvoiceId(1L);
		assertEquals(1L, createReq.getInvoiceId());

	}
	
	@Test
	public void setPriceTest() {
		createReq.setPrice(22.99);
		assertEquals(22.99, createReq.getPrice(),PRECISION);

	}

	@Test
	public void getPriceTest() {
		createReq.setPrice(22.99);
		assertEquals(22.99, createReq.getPrice(),PRECISION);
	}
    

	@Test
	public void setUserIdTest() {
		createReq.setUserId(1L);
		assertEquals(1L, createReq.getUserId());

	}
	
	@Test
	public void getUserIdTest() {
		createReq.setUserId(1L);
		assertEquals(1L, createReq.getUserId());

	}

	@Test
	public void setFirstNameTest() {
		createReq.setFirstName("test");
		assertEquals("test",createReq.getFirstName());

	}
	@Test
	public void getFirstNameTest() {
		createReq.setFirstName("test");
		assertEquals("test",createReq.getFirstName());
		

	}
	@Test
	public void setTitleTest() {
		createReq.setTitle("Title");
		assertEquals("Title",createReq.getTitle());

	}
	
	@Test
	public void getTitleTest() {
		createReq.setTitle("title");
		assertEquals("title",createReq.getTitle());
		

	}
	
	
	@Test
	public void setLastName() {
		createReq.setLastName("LastName");
		assertEquals("LastName",createReq.getLastName());

	}
	
	@Test
	public void getLastNameTest() {
		createReq.setLastName("LastName");
		assertEquals("LastName",createReq.getLastName());

	}
}
