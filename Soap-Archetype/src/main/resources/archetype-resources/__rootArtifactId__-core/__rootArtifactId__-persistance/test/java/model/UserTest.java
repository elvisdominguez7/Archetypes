package ${package}.model;


import org.junit.Test;

import static org.junit.Assert.*;



public class UserTest {

	private User createReq = new User();
	

	
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
	public void setLastName() {
		createReq.setLastName("Testing");
		assertEquals("Testing",createReq.getLastName());

	}
	
	@Test
	public void getLastNameTest() {
		createReq.setLastName("Testing");
		assertEquals("Testing",createReq.getLastName());

	}



}
