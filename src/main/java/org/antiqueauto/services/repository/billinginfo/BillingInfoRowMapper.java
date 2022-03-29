package org.antiqueauto.services.repository.billinginfo;

import org.antiqueauto.services.domain.BillingInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingInfoRowMapper implements RowMapper<BillingInfo> {

    @Override
    public BillingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BillingInfo(
                rs.getInt("id"),
                rs.getDouble("hourly_rate"),
                rs.getDouble("materials_percentage"),
                rs.getDouble("insurance_rate"),
                rs.getDate("first_invoice"),
                rs.getBoolean("first_invoice_mailed"),
                rs.getDate("second_invoice"),
                rs.getBoolean("second_invoice_mailed")
        );
    }
}
