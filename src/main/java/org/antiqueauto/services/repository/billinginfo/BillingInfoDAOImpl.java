package org.antiqueauto.services.repository.billinginfo;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BillingInfoDAOImpl implements BillingInfoDAO {

    private final JdbcTemplate jdbcTemplate;
    private final static String INVALID_DATA_MESSAGE = "Invalid Data Access";

    public BillingInfoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from billing_info where id=?;";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }
}
