#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import ${package}.exception.InvalidInputException;
import ${package}.exception.UserAllreadyExistException;
import ${package}.exception.UserNotFoundException;
import ${package}.mapper.UserRowMapper;

import static ${package}.constant.ErrorFault.NO_USER_FOUND;
import static ${package}.constant.ErrorFault.USER_ALREADY_EXIST;
import static ${package}.constant.ErrorFault.USERS_DO_NOT_EXIST;
import static ${package}.constant.ErrorFault.INVALID_INPUT;

import ${package}.model.*;



@Repository
@Component
public class UserDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private JdbcTemplate findAll;

	@Autowired
	private IOUtils createUser;

	@Autowired
	private IOUtils retrieveUser;

	@Autowired
	private IOUtils retrieveAllUsers;

	@Autowired
	private IOUtils updateUser;

	@Autowired
	private IOUtils deleteUser;

	@Transactional
	public void save(User user) throws InvalidInputException, UserAllreadyExistException {
		if (user == null) {
			throw new InvalidInputException(INVALID_INPUT + "NULL");
		}

		SqlParameterSource namedParameters = new MapSqlParameterSource();

		((MapSqlParameterSource) namedParameters).addValue("id", user.getUserId());
		((MapSqlParameterSource) namedParameters).addValue("firstname", user.getFirstName());
		((MapSqlParameterSource) namedParameters).addValue("lastname", user.getLastName());

		try {
			jdbcTemplate.update(createUser.toString(), namedParameters);

		} catch (DataAccessException dae) {
			throw new UserAllreadyExistException(USER_ALREADY_EXIST + ": " + user.getFirstName());

		}

	}


	@Transactional(readOnly = true)
	public User retrieve(Long id) throws InvalidInputException, UserNotFoundException {

		User response;

		if (id == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + id);
		}
		try {
			response = (User) jdbcTemplate.queryForObject(retrieveUser.toString(),
					new MapSqlParameterSource("userId", Long.valueOf(id)), new UserRowMapper());
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + ": " + Long.valueOf(id));

		}
		return response;

	}

	@Transactional(readOnly = true)
	public Collection<User> retrieveAllUsers() throws UserNotFoundException {

		List<User> users = new ArrayList<User>();

		try {
			List<Map<String, Object>> rows = findAll.queryForList(retrieveAllUsers.toString());
			for (Map<?, ?> row : rows) {
				User user = new User();
				user.setUserId(Long.parseLong(String.valueOf(row.get("user_id"))));
				user.setFirstName((String) row.get("first_name"));
				user.setLastName((String) row.get("last_name"));
				users.add(user);
			}

		} catch (DataAccessException dae) {
			throw new UserNotFoundException(USERS_DO_NOT_EXIST);

		}
		return users;

	}

	@Transactional
	public void update(User update) throws UserNotFoundException, InvalidInputException {

		if (update == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + "NULL");
		}

		SqlParameterSource namedParameters = new MapSqlParameterSource();

		((MapSqlParameterSource) namedParameters).addValue("userId", update.getUserId());
		((MapSqlParameterSource) namedParameters).addValue("firstName", update.getFirstName());
		((MapSqlParameterSource) namedParameters).addValue("lastName", update.getLastName());

		try {
			jdbcTemplate.update(updateUser.toString(), namedParameters);
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + ": " + update.getFirstName());

		}

	}

	@Transactional
	public void delete(long id) throws UserNotFoundException, InvalidInputException {
		if (Long.valueOf(id) == null) {
			throw new InvalidInputException(INVALID_INPUT);
		}
		try {
			jdbcTemplate.update(deleteUser.toString(), new MapSqlParameterSource("userId", id));
		} catch (DataAccessException dae) {
			throw new UserNotFoundException(NO_USER_FOUND + ": " + Long.valueOf(id));

		}

	}

}
