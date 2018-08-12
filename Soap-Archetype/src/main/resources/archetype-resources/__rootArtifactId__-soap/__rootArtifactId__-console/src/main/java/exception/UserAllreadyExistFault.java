package ${package}.exceptions;


import javax.xml.ws.WebFault;

import ${package}.exceptions.CustomerAllreadyExistFaultDetails;

@WebFault(name = "UserAllreadyExistFault", targetNamespace = "http://${serviceName}.${package}")
public class UserAllreadyExistFault extends Exception {
	
	private UserAllreadyExistFaultDetails customerAllreadyExistFaultDetails;

 
    
    public UserAllreadyExistFault(String message) {
        super(message);
    }
    
    public UserAllreadyExistFault(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAllreadyExistFault(String message, UserAllreadyExistFaultDetails custFaultDetails) {
        super(message);
        this.customerAllreadyExistFaultDetails = custFaultDetails;
    }

    public UserAllreadyExistFault(String message, UserAllreadyExistFaultDetails custFaultDetails, Throwable cause) {
        super(message, cause);
        this.customerAllreadyExistFaultDetails = custFaultDetails;
    }

    public UserAllreadyExistFaultDetails getFaultInfo() {
        return this.customerAllreadyExistFaultDetails;
    }
    
} 
