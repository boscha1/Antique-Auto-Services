package org.antiqueauto.services.repository.car;

import org.antiqueauto.services.domain.Car;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    private final JdbcTemplate jdbcTemplate;
    private final static String INVALID_DATA_MESSAGE = "Invalid Data Access";

    public CarDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(Integer car) {
        return null;
    }

    @Override
    public Car save(Car customer) {
        return null;
    }

    @Override
    public List<Car> findByCustomerId(Integer customerId) {
        try {
            String query = "select car.code,\n" +
                    "       car.make,\n" +
                    "       car.model,\n" +
                    "       car.year,\n" +
                    "       car.notes,\n" +
                    "       bi.hourly_rate,\n" +
                    "       bi.materials_percentage,\n" +
                    "       bi.insurance_rate,\n" +
                    "       bi.first_invoice,\n" +
                    "       bi.first_invoice_mailed,\n" +
                    "       bi.second_invoice,\n" +
                    "       bi.second_invoice_mailed\n" +
                    "from car join billing_info bi on car.id = bi.car_id\n" +
                    "where customer_id=?;";
            return jdbcTemplate.query(query, new CarRowMapper(), customerId);
        } catch (DataAccessException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
