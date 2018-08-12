
package ${package}.exceptions;

import javax.xml.ws.WebFault;


@WebFault(name = "NoDataFoundFaultDetails", targetNamespace = "http://${serviceName}.${package}")
public class NoDataFoundFault extends Exception {
   
	
	private NoDataFoundFaultDetails noDataFoundFaultDetails;

    public NoDataFoundFault() {
        super();
    }
    
    public NoDataFoundFault(String message) {
        super(message);
    }
    
    public NoDataFoundFault(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFoundFault(String message, NoDataFoundFaultDetails noDataFoundFaultDetails) {
        super(message);
        this.noDataFoundFaultDetails = noDataFoundFaultDetails;
    }

    public NoDataFoundFault(String message, NoDataFoundFaultDetails noDataFoundFaultDetails, Throwable cause) {
        super(message, cause);
        this.noDataFoundFaultDetails = noDataFoundFaultDetails;
    }

    public NoDataFoundFaultDetails getFaultInfo() {
        return this.noDataFoundFaultDetails;
    }
}
