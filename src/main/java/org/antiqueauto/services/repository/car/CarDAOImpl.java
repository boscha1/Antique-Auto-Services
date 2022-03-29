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
        String sql = "select car.id," +
                "       car.code,\n" +
                "       car.make,\n" +
                "       car.model,\n" +
                "       car.year,\n" +
                "       car.notes,\n" +
                "       bi.id,\n" +
                "       bi.hourly_rate,\n" +
                "       bi.materials_percentage,\n" +
                "       bi.insurance_rate,\n" +
                "       bi.first_invoice,\n" +
                "       bi.first_invoice_mailed,\n" +
                "       bi.second_invoice,\n" +
                "       bi.second_invoice_mailed\n" +
                "from car join billing_info bi on car.id = bi.car_id\n" +
                "where customer_id=?;";
        try {
            return jdbcTemplate.query(sql, new CarRowMapper(), customerId);
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public void delete(Integer id) {
        String carSql = "delete from car where id=?;";
        String billingSql = "delete from billing_info where car_id=?;";
        try {
            jdbcTemplate.update(billingSql, id);
            jdbcTemplate.update(carSql, id);
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }
}
