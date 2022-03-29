package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getInt("customer_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                null
        );
    }
}
