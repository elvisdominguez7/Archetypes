package ${package}.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ${package}.model.User;




@Component
public class UserRowMapper implements RowMapper<Object> {

public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	
	User user = new User();
    
   
    user.setFirstName(StringUtils.trimLeadingWhitespace(rs.getString("first_name")));
    user.setLastName(StringUtils.trimLeadingWhitespace(rs.getString("last_name")));
    
    return user;
}


}
