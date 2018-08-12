package ${package};

import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ${package}.service.SampleService;
import ${package}.service.SampleServiceImpl;


@Configuration
public class ServiceConfig {
	
	@Bean
	  public UserManagementService userService(){
		  return new UserManagementServiceImpl();
	  }

  @Bean
  public Endpoint endpoint(UserManagementService userService) {
      return new EndpointImpl(userService);
  }
    
 
    
    
}
