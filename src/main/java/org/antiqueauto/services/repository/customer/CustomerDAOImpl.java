package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final static String INVALID_DATA_MESSAGE = "Invalid Data Access";

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("customer")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Customer> findAll() {
        String sql = "select * from customer;";
        try {
            return jdbcTemplate.query(sql, new CustomerRowMapper());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Optional<Customer> findById(Integer customerId) {
        String sql = "select * from customer where id=?;";
        try {
            return jdbcTemplate.query(sql, new CustomerRowMapper(), customerId)
                    .stream()
                    .findFirst();
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("first_name", customer.getFirstName())
                .addValue("last_name", customer.getLastName());
        try {
            Number customerId = simpleJdbcInsert.executeAndReturnKey(params);
            return findById(customerId.intValue());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        String sql = "update customer\n" +
                "set first_name=?," +
                "last_name=?\n" +
                "where id=?;";
        try {
            jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getId());
            return findById(customer.getId());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from customer where id=?;";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
