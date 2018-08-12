#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ${package}.config.SecurityPolicyConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;




@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SecurityPolicyConfig.class)
@WebMvcTest(controllers = InvoiceController.class)
public class InvoiceControllerTest{
	
	
	@Autowired
    private WebApplicationContext context;
	
	 @Autowired
     private MockMvc mockMvc;
	
	
	
	  @Before
	  public void setup() {  		    
		    mockMvc = MockMvcBuilders
	            .webAppContextSetup(context)
	            .apply(springSecurity())
	            .build();
	    }
	  
	 
	@Test
	@WithMockUser(username = "user",password = "user1234", roles = {"ASSOCIATES"})
	public void createInvoice() throws Exception {			
		this.mockMvc.perform(post("/invoice")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
					.param("invoiceId", "1")
					.param("firstName", "test")
					.param("lastName", "testing"))
					.andExpect(status().isCreated())
					.andExpect(content().contentType("application/json"))
					.andReturn();
		
		
	}

	@Test
	@WithMockUser(username = "user",password = "user1234", roles = {"ASSOCIATES"})
	public void retrieveInvoiceByCustomerId() throws Exception {
		this.mockMvc.perform(get("/invoices/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
					.param("invoiceId", "1"))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType("application/json"))
    				.andExpect(jsonPath(".invoiceId").value("1"))
    				.andExpect(jsonPath(".firstName").value("test"))
    				.andExpect(jsonPath(".lastName").value("testing"))
    				.andReturn();

		
	}
	@Test
	@WithMockUser(username = "user",password = "user1234", roles = {"ASSOCIATES"})
	public void updateInvoice() throws Exception {			
		this.mockMvc.perform(post("/invoice")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
					.param("invoiceId", "1")
					.param("firstName", "updated")
					.param("lastName", "updated"))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andReturn();
		
		
	}

	

	@Test
	@WithMockUser(username = "user",password = "user1234", roles = {"ASSOCIATES"})
	public void deleteInvoice() throws Exception {			
		this.mockMvc.perform(delete("/invoices/1")
					.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
					.param("invoiceId", "1"))
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"));
					
			
		
	}
	


}
