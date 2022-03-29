package org.antiqueauto.services.repository.car;

import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.repository.billinginfo.BillingInfoRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {
    private final BillingInfoRowMapper rowMapper;

    public CarRowMapper(BillingInfoRowMapper rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Car(
                rs.getString("make"),
                rs.getString("model"),
                rs.getLong("year"),
                rs.getString("notes"),
                rowMapper.mapRow(rs, rowNum)
        );
    }
}
