#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ${package}.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;






@Component
public class UserRowMapper implements RowMapper<Object> {

public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	
	User user = new User();
    
   
    user.setFirstName(StringUtils.trimLeadingWhitespace(rs.getString("first_name")));
    user.setLastName(StringUtils.trimLeadingWhitespace(rs.getString("last_name")));
    
    return user;
}


}
