#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.service.Parameter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;
import static springfox.documentation.builders.PathSelectors.regex;


 
@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
	
	@Value("${symbol_dollar}{swagger.organization.name}")
    private String org;
	
	@Value("${symbol_dollar}{swagger.developer.name}")
    private String devName;
	
	@Value("${symbol_dollar}{swagger.developer.email}")
    private String devEmail;
	
	@Value("${symbol_dollar}{swagger.organization.url}")
    private String url;
	
	
	@Value("${symbol_dollar}{swagger.basePkg}")
    private String basePkg;
	
	
	@Value("${symbol_dollar}{swagger.path}")
    private String path;
	
	@Value("${symbol_dollar}{swagger.title}")
    private String title;
	
	@Value("${symbol_dollar}{swagger.description}")
    private String description;
	
	@Value("${symbol_dollar}{swagger.api.version}")
    private String apiVersion;

	
	@Bean
    public Docket api() {
        
        return new Docket(DocumentationType.SWAGGER_2)
        		.globalOperationParameters(
                        new ArrayList(ssoTokenHeaderSetUp()))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePkg))
                .paths(regex(path))
                .build()
                .apiInfo(metaData()
                );
    }
	
	private List<Parameter> ssoTokenHeaderSetUp(){
		
		ParameterBuilder header = new ParameterBuilder();
        
        header.name("X-USERMGMT_SSO_TOKEN")
               .modelRef(new ModelRef("string"))
        	   .parameterType("header")
        	   .required(true)
        	   .build();
        
        
       List<Parameter> params = new ArrayList<Parameter>();
        			   params.add((Parameter) header.build());
        			  
       return params;
		
		
	}
    
    private ApiInfo metaData() {
    	
    	  return new ApiInfoBuilder()
                  .title(title)
                  .description(description)
                  .termsOfServiceUrl("http://locahost/${symbol_dollar}{artifactId}-rs")
                  .contact(new Contact(devName,"",devEmail))
                  .license(org)
                  .licenseUrl(url)
                  .version(apiVersion)
                  .build();
    }
}

