package ${package}.dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


import ${package}.dao.UserDao;
import ${package}.exception.InvalidInputException;
import ${package}.exception.UserNotFoundException;
import ${package}.model.User;


public class UserDaoTest {
	
	private  User request = new User();
	private User response = new User();
	private User retrieveRequest = new User();
	private User update = new User();
	
	@Mock
	private UserDao repoMock;
	
	
	
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
       
    }

	@Test
    public void testMockCreation(){
        assertNotNull(repoMock);
        
       
		request.setUserId(1L);
	    request.setFirstName("test");
	    request.setLastName("test");
	    
	    
	    retrieveRequest.setUserId(1L);
	   
	    
	    response.setUserId(1L);
	    response.setFirstName("test");
	    response.setLastName("test");
	    
	    update.setUserId(1L);
	    update.setFirstName("test");
	    update.setLastName("testing");
    }
	

	@Test
	public void save() throws Exception {
		
		doNothing().when(repoMock).save(request);	
	}

	@Test
	public void retrieve() throws Exception {
	    
		when(repoMock.retrieve(1L)).thenReturn(response);
	    assertEquals(response, repoMock.retrieve(1L));
		
	}


	@Test
	public void updateUser() throws Exception {
	
	 doNothing().when(repoMock).update(update);
		
	}

	@Test
	public void deleteUser() throws Exception {
		doNothing().when(repoMock).delete(1L);
			
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test(expected = InvalidInputException.class)
	public void testInputInvalidExceptionWhenRetrieve()
			throws InvalidInputException, UserNotFoundException {
		
	      when(repoMock.retrieve((Long) null)).thenThrow(InvalidInputException.class);

		repoMock.retrieve((Long) null);

	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = UserNotFoundException.class)
	public void testInvoiceNotFoundExceptionWhenRetrieveAllUsers()
			throws  UserNotFoundException {
		
	      when(repoMock.retrieveAllUsers()).thenThrow(UserNotFoundException.class);

		repoMock.retrieveAllUsers();

	}

	
	
	
}
