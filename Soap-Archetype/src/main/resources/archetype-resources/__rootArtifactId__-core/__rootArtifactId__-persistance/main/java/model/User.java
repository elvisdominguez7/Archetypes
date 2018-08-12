package ${package}.model;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="User Model for CRUD Operations")
@Component
public class User extends ResourceSupport{
	
	

	@ApiModelProperty(position = 0, required = true, value = "Primary Key")
	private long userId;

	@ApiModelProperty(position = 1, required = true, value = "User First Name")
	private String firstName;
	
	@ApiModelProperty(position = 2, required = true, value = "User Last Name")
	private String lastName;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		this.userId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	

}
