package ${package}.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ${package}.config.SecurityPolicyConfig;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SecurityPolicyConfig.class)
@WebMvcTest(controllers = UserManagementController.class)
public class UserManagementControllerTest {
	
	@Autowired
    private WebApplicationContext context;
	
	 @Autowired
     private MockMvc mockMvc;
	 
	 @Autowired 
	 private FilterChainProxy springSecurityFilterChain;
	
	
	  @Before
	  public void setup() {    
		    mockMvc = MockMvcBuilders
	            .webAppContextSetup(context)
	            .addFilters(springSecurityFilterChain)
	            .apply(springSecurity())
	            .build();
	    }
	  
	 
	@Test
	@WithMockUser(username = "user",password = "user1234", roles = {"ASSOCIATES" })
	public void createuser() throws Exception {	
		this.mockMvc.perform(post("/customer")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
					.param("userId", "4")
					.param("firstName", "test4")
					.param("lastName", "testing4"))
					.andExpect(status().isCreated())
					.andExpect(content().contentType("application/json"))
					.andReturn();
		
		
	}

	@Test
	@WithMockUser(username = "user",password = "user1234", roles = {"ASSOCIATES"})
	public void retrieveuser() throws Exception {
		this.mockMvc.perform(get("/users/4")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType("application/json"))
    				.andExpect(jsonPath(".userId").value("4"))
    				.andExpect(jsonPath(".firstName").value("test4"))
    				.andExpect(jsonPath(".lastName").value("testing4"))
    				.andReturn();

		
	}

	@Test
	@WithMockUser(username = "userAdmin",password = "userAdmin1234", roles = {"ADMINISTRATORS" })
	public void updateuser() throws Exception {
		this.mockMvc.perform(put("/users")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
					.param("userId", "4")
					.param("firstName", "Updated4")
					.param("lastName", "Updated4"))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath(".userId").value("4"))
					.andExpect(jsonPath(".firstName").value("Updated4"))
					.andExpect(jsonPath(".lastName").value("Updated4"))
					.andReturn();
		
	}

	@Test
	@WithMockUser(username = "userAdmin",password = "userAdmin1234", roles = {"ADMINISTRATORS" })
	public void deleteuser() throws Exception {	
		this.mockMvc.perform(delete("/users/4")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"));
					
			
		
	}
	
	
	
}
