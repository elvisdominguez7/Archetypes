package ${package}.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;


import org.springframework.validation.annotation.Validated;


import ${package}.exceptions.CustomerAllreadyExistFault;
import ${package}.exceptions.InvalidInputFault;
import ${package}.exceptions.NoDataFoundFault;

import ${package}.model.CreateUserRequest;
import ${package}.model.DeleteUserRequest;
import ${package}.model.RetrieveUserRequest;
import ${package}.model.RetrieveUserResponse;
import ${package}.model.UpdateUserRequest;



@WebService(name="${serviceName}")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@Validated
public interface UserManagementService {

	
    @WebMethod(action = "http://local.test.statefarm.com:8080/${artifactId}/services/${serviceName}/createUser")
    public void createUser(@WebParam(partName = "user",name = "CreateUserRequest", targetNamespace = "http://${serviceName}.${package}")@Valid CreateUserRequest user)throws UserAllreadyExistFault, InvalidInputFault;
    

    			
    @WebResult(name = "RetrieveUserResponse", targetNamespace = "http://${serviceName}.${package}", partName = "retrieveUserResponse")
    @WebMethod(action = "http://localhost:8080/${artifactId}/services/${serviceName}/retrieveUser")
    public RetrieveUserResponse retrieveUser(
        @WebParam(partName = "user", name = "RetrieveUserRequest", targetNamespace = "http://${serviceName}.${package}")
        @Valid RetrieveUserRequest parameters
    ) throws NoDataFoundFault, InvalidInputFault;
    	
   
    @WebMethod(action = "http://localhost:8080/${artifactId}/services/${serviceName}/updateUser")
    public void updateUser(@WebParam(partName = "user",name = "UpdateUserRequest", targetNamespace = "http://${serviceName}.${package}")@Valid UpdateUserRequest user)throws NoDataFoundFault, InvalidInputFault;
    		
    		
    
   
    @WebMethod(action = "http://localhost:8080/${artifactId}/services/${serviceName}/deleteUser")
    public void deleteUser(@WebParam(partName = "userId",name = "DeleteUserRequest", targetNamespace = "http://${serviceName}.${package}")@Valid DeleteUserRequest user)throws NoDataFoundFault, InvalidInputFault;
}



