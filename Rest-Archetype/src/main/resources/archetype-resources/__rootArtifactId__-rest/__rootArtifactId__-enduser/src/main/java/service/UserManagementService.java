#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${package}.dao.UserDao;
import ${package}.exception.UserAllreadyExistException;
import ${package}.exception.UserNotFoundException;
import ${package}.exception.InvalidInputException;
import ${package}.model.User;

import static ${package}.constant.ErrorFault.INVALID_INPUT;
import static ${package}.constant.ErrorFault.USER_ALREADY_EXIST;
import static ${package}.constant.ErrorFault.NO_USER_FOUND;

@Service
public class UserManagementService {

	@Autowired
	private UserDao dao;
	
	@Transactional()
	public void saveUser(User user) throws InvalidInputException, UserAllreadyExistException, UserNotFoundException {

		User currentCust = null;

		if (user == null) {
			throw new InvalidInputException(INVALID_INPUT + "NULL");
		}
		try {
			currentCust = (User) dao.retrieve(user.getUserId());

			if (currentCust != null) {
				throw new UserAllreadyExistException(USER_ALREADY_EXIST + ": " + user.getFirstName());

			}
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + ":" + user.getUserId());

		}

		dao.save(user);
	}

	@Transactional(readOnly = true)
	public User retrieveUser(long id) throws UserNotFoundException, InvalidInputException {
		User response;

		if (Long.valueOf(id) == null) {
			throw new InvalidInputException(INVALID_INPUT + id);
		}

		try {
			response = dao.retrieve(id);
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + id);

		}

		return response;

	}

	@Transactional(readOnly = true)
	public Collection<User> retrieveAllUsers() throws UserNotFoundException {
		List<User> users = new ArrayList<User>();

		try {
			users = (List<User>) dao.retrieveAllUsers();
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND);

		}

		return users;

	}

	@SuppressWarnings("null")
	@Transactional()
	public void updateUser(User request) throws UserNotFoundException, InvalidInputException {

		if (request == null) {
			throw new InvalidInputException(INVALID_INPUT + request.getId());
		}

		User currentCust = null;
		try {
			currentCust.setFirstName(request.getFirstName());
			currentCust.setLastName(request.getLastName());

			dao.update(currentCust);

		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + ": " + request.getFirstName());
		}

	}
	
	@Transactional()
	public void deleteUser(long id) throws UserNotFoundException, InvalidInputException {

		User currentId = null;

		if (Long.valueOf(id) == null) {
			throw new InvalidInputException(INVALID_INPUT + id);
		}
		try {
			currentId = dao.retrieve(id);

			if (currentId.getUserId() != id) {
				throw new UserNotFoundException(NO_USER_FOUND + id);

			}
			dao.delete(id);
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + id);
		}

	}

}
