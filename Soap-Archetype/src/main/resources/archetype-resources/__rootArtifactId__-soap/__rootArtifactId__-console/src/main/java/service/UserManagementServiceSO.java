package ${package}.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static ${package}.constants.ErrorFaults.INVALID_INPUT_FAULT;
import static ${package}.constants.ErrorFaults.USER_ALREADY_EXIST_FAULT;
import static ${package}.constants.ErrorFaults.NO_USER_FOUND_FAULT;

import ${package}.exceptions.UserAllreadyExistFault;
import ${package}.exceptions.InvalidInputFault;
import ${package}.exceptions.NoDataFoundFault;

import ${package}.model.CreateUserRequest;
import ${package}.model.DeleteUserRequest;
import ${package}.model.RetrieveUserRequest;
import ${package}.model.RetrieveUserResponse;
import ${package}.model.UpdateUserRequest;

import ${package}.dao.SampleServiceDaoImpl;




@Component
public class UserManagementServiceSO {
	

	@Autowired
	private UserManagementServiceDaoImpl dao;

	public void saveUser(CreateUserRequest user) throws InvalidInputFault, UserAllreadyExistFault

	{
		if (user == null) {
			throw new InvalidInputFault(INVALID_INPUT_FAULT);

		}
		try {
			dao.save(user);

		} catch (UserAllreadyExistFault cae) {
			throw new UserAllreadyExistFault(USER_ALREADY_EXIST_FAULT);

		}

	}

	public RetrieveUserResponse retrieveUser(RetrieveUserRequest request)
			throws NoDataFoundFault, InvalidInputFault

	{
		RetrieveUserResponse response = null;
		if (request == null) {
			throw new InvalidInputFault(INVALID_INPUT_FAULT);
		}
		try {
			response = dao.retrieve(request.getUserId());
		} catch (NoDataFoundFault ndf) {
			throw new NoDataFoundFault(NO_USER_FOUND_FAULT);

		}

		return response;

	}

	public void updateUser(UpdateUserRequest request) throws NoDataFoundFault, InvalidInputFault

	{
		if (request == null) {
			throw new InvalidInputFault(INVALID_INPUT_FAULT);
		}
		try {
			dao.update(request);

		} catch (NoDataFoundFault ndf) {
			throw new NoDataFoundFault();
		}

	}

	public void deleteUser(DeleteUserRequest request) throws NoDataFoundFault, InvalidInputFault {
		if (request == null) {
			throw new InvalidInputFault(INVALID_INPUT_FAULT);
		}
		try {
			dao.delete(request.getUserId());
		} catch (NoDataFoundFault ndf) {
			throw new NoDataFoundFault(NO_USER_FOUND_FAULT);
		}

	}

}
