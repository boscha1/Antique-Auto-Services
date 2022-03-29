package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final static String INVALID_DATA_MESSAGE = "Invalid Data Access";

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "select * from customer";
        try {
            return jdbcTemplate.query(sql, new CustomerRowMapper());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Customer findById(Integer customerId) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }
}
