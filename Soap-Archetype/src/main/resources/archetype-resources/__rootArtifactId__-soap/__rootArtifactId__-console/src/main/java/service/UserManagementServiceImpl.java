package ${package}.service;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;



import ${package}.exceptions.UserAllreadyExistFault;
import ${package}.exceptions.InvalidInputFault;
import ${package}.exceptions.NoDataFoundFault;

import ${package}.so.UserServiceSO;

import ${package}.model.CreateUserRequest;
import ${package}.model.DeleteUserRequest;
import ${package}.model.RetrieveUserRequest;
import ${package}.model.RetrieveUserResponse;
import ${package}.model.UpdateUserRequest;



@Component
@WebService(endpointInterface = "${package}.service.UserManagementService", serviceName="${serviceName}") 
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	private UserServiceSO so;

	@Override
	@PreAuthorize("hasRole('TESTERS')")
	public void createUser(CreateUserRequest user) throws UserAllreadyExistFault, InvalidInputFault{
			so.saveUser(user);
	
		
	}

	@Override
	@PreAuthorize("hasRole('TESTERS') or hasRole('ADMINISTRATORS') ")
	public RetrieveUserResponse retrieveUser(RetrieveUserRequest id)
			throws NoDataFoundFault, InvalidInputFault {
		return so.retrieveUser(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMINISTRATORS')")
	public void updateUser(UpdateUserRequest user) throws NoDataFoundFault, InvalidInputFault {
		so.updateUser(user);
		
	}

	@Override
	@PreAuthorize("hasRole('ADMINISTRATORS')")
	public void deleteUser(DeleteUserRequest id) throws NoDataFoundFault, InvalidInputFault {
		so.deleteUser(id);
		
	}
	

}
