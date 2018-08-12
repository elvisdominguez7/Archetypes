package ${package}.exceptions;

import javax.xml.ws.WebFault;

import ${package}.exceptions.InvalidInputFaultDetails;


@WebFault(name = "InvalidInputFaultDetails", targetNamespace = "http://${serviceName}.${package}")
public class InvalidInputFault extends Exception {
    
	
	private InvalidInputFaultDetails invalidInputFaultDetails;

    public InvalidInputFault() {
        super();
    }
    
    public InvalidInputFault(String message) {
        super(message);
    }
    
    public InvalidInputFault(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputFault(String message, InvalidInputFaultDetails invalidInputFaultDetails) {
        super(message);
        this.invalidInputFaultDetails = invalidInputFaultDetails;
    }

    public InvalidInputFault(String message, InvalidInputFaultDetails invalidInputFaultDetails, Throwable cause) {
        super(message, cause);
        this.invalidInputFaultDetails = invalidInputFaultDetails;
    }

    public InvalidInputFaultDetails getFaultInfo() {
        return this.invalidInputFaultDetails;
    }
}
