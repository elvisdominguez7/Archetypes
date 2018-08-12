package ${package}.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.stereotype.Component;

/**
 * <p>Java class for RetrieveCustomersRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateUserRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerId" type="{http://${serviceName}.${package}}customerId" />
 *         &lt;element name="firstName" type="{http://${serviceName}.${package}}firstName"/>
 *         &lt;element name="LastName" type="{http://${serviceName}.${package}}lasttName"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateUserRequest", namespace = "${serviceName}.${package}", propOrder = {
    "customerId",
    "firstName",
    "lastName"
})
@Component
public class UpdateUserRequest {
	
	@XmlElement(required = true)
	private Long userId;
	
	@XmlElement(required = true)
	private String firstName;
	
	@XmlElement(required = true)
	private String lastName;

	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
