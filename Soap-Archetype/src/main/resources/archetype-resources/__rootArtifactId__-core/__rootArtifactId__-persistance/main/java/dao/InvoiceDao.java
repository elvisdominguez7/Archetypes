
package ${package}.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import ${package}.exception.InvalidInputException;
import ${package}.exception.InvoiceAllreadyExistException;
import ${package}.exception.InvoiceNotFoundException;
import ${package}.mapper.InvoiceRowMapper;
import ${package}.model.Invoice;

import static ${package}.constant.ErrorFault.INVALID_INPUT;
import static ${package}.constant.ErrorFault.INVOICES_DO_NOT_EXIST;
import static ${package}.constant.ErrorFault.NO_USER_FOUND;
import static ${package}.constant.ErrorFault.NO_INVOICE_FOUND;



@Repository
@Component
public class InvoiceDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private JdbcTemplate findAllInvoices;

	@Autowired
	private IOUtils createInvoice;

	@Autowired
	private IOUtils retrieveInvoice;

	@Autowired
	private IOUtils updateInvoice;

	@Autowired
	private IOUtils deleteInvoice;

	@Transactional
	public void save(Invoice Invoice) throws InvalidInputException, InvoiceAllreadyExistException {
		if (Invoice == null) {
			throw new InvalidInputException(INVALID_INPUT + "NULL");
		}

		SqlParameterSource namedParameters = new MapSqlParameterSource();

		((MapSqlParameterSource) namedParameters).addValue("userId", Invoice.getUserId());
		((MapSqlParameterSource) namedParameters).addValue("price", Invoice.getPrice());
		((MapSqlParameterSource) namedParameters).addValue("title", Invoice.getTitle());
		((MapSqlParameterSource) namedParameters).addValue("firstName", Invoice.getFirstName());
		((MapSqlParameterSource) namedParameters).addValue("lastName", Invoice.getLastName());

		try {

			jdbcTemplate.update(createInvoice.toString(), namedParameters);
		} catch (DataAccessException dae) {
			throw new InvoiceAllreadyExistException(NO_INVOICE_FOUND + ": " + Invoice.getInvoiceId());

		}

	}

	// All SQL Query MUST BE TRANSACTIONAL!!
	/*
	 * All write transactions are routed to the ISC data center. Data is then
	 * propagated to the other data centers. Read only transactions should set
	 * the @Transactional readOnly attribute to true. This allows the Cloud SDK to
	 * route JDBC calls more efficiently to the closest data center.
	 * 
	 * NOTE: If you are are quickly reading data that was just written in a
	 * subsequent transaction you should consider marking that read call as
	 * readOnly=false to ensure that you are able to retrieve your data as it may
	 * not have had time to propagate out to all data centers.
	 */

	@Transactional(readOnly = true)
	public Invoice retrieve(Long id) throws InvalidInputException, InvoiceNotFoundException {
		Invoice response;

		if ( id == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + id);
		}
		try {
			response = (Invoice) jdbcTemplate.queryForObject(retrieveInvoice.toString(),
					new MapSqlParameterSource("invoiceId", id), new InvoiceRowMapper());
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(INVOICES_DO_NOT_EXIST + ": " + Long.valueOf(id));

		}
		return response;

	}

	@Transactional(readOnly = true)
	public Collection<Invoice> retrieveAllInvoices() throws InvoiceNotFoundException {
		List<Invoice> Invoices = new ArrayList<Invoice>();

		try {
			List<Map<String, Object>> rows = findAllInvoices.queryForList(retrieveInvoice.toString());

			for (Map<?, ?> row : rows) {
				Invoice Invoice = new Invoice();
				Invoice.setInvoiceId(Long.parseLong(String.valueOf(row.get("invoice_id"))));
				Invoice.setPrice(Double.parseDouble(String.valueOf(row.get("price"))));
				Invoice.setTitle((String) row.get("title"));
				Invoice.setFirstName((String) row.get("first_name"));
				Invoice.setLastName((String) row.get("last_name"));
				Invoice.setUserId(Long.parseLong(String.valueOf(row.get("user_id"))));
				Invoices.add(Invoice);
			}
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(INVOICES_DO_NOT_EXIST);

		}

		return Invoices;

	}
	
	@Transactional
	public void update(Invoice update) throws InvoiceNotFoundException, InvalidInputException {

		if (update == null) {
			throw new InvalidInputException(INVALID_INPUT + ": " + "NULL");
		}

		SqlParameterSource namedParameters = new MapSqlParameterSource();

		((MapSqlParameterSource) namedParameters).addValue("invoiceId", update.getUserId());
		((MapSqlParameterSource) namedParameters).addValue("price", update.getPrice());
		((MapSqlParameterSource) namedParameters).addValue("title", update.getTitle());
		((MapSqlParameterSource) namedParameters).addValue("firstName", update.getFirstName());
		((MapSqlParameterSource) namedParameters).addValue("lastName", update.getLastName());

		try {
			jdbcTemplate.update(updateInvoice.toString(), namedParameters);
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(NO_USER_FOUND + ": " + update.getInvoiceId());

		}

	}

	@Transactional
	public void delete(long id) throws InvoiceNotFoundException, InvalidInputException {
		if (Long.valueOf(id) == null) {
			throw new InvalidInputException(INVALID_INPUT);
		}
		try {
			jdbcTemplate.update(deleteInvoice.toString(), new MapSqlParameterSource("invoiceId", id));
		} catch (DataAccessException dae) {
			throw new InvoiceNotFoundException(NO_INVOICE_FOUND + ": " + id);

		}

	}

}
