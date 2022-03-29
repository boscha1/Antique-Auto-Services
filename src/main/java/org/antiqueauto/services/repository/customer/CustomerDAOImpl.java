package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final static String INVALID_DATA_MESSAGE = "Invalid Data Access";

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public Customer save(Customer customer) {
        String sql = "insert into customer(first_name, last_name) values (?, ?);";
        try {
            jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        String sql = "update customer\n" +
                "set first_name=?," +
                "last_name=?\n" +
                "where id=?;";
        try {
            jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getId());
            return customer;
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
