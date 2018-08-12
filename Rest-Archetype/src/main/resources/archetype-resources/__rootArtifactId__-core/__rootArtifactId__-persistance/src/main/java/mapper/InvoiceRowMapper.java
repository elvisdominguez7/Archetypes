#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import ${package}.model.Invoice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



@Component
public class InvoiceRowMapper implements RowMapper<Object> {

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		Invoice invoice = new Invoice();

		invoice.setUserId(rs.getLong("user_id"));

		invoice.setPrice(rs.getDouble("price"));

		invoice.setTitle(StringUtils.trimLeadingWhitespace((rs.getString("title"))));
		
		invoice.setFirstName(StringUtils.trimLeadingWhitespace((rs.getString("first_name"))));
		invoice.setLastName(StringUtils.trimLeadingWhitespace(rs.getString("last_name")));

		return invoice;
	}

}
