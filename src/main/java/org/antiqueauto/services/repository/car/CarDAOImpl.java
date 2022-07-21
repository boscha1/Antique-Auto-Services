package org.antiqueauto.services.repository.car;

import org.antiqueauto.services.domain.BillingInfo;
import org.antiqueauto.services.domain.Car;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDAOImpl implements CarDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final static String INVALID_DATA_MESSAGE = "Invalid Data Access";

    public CarDAOImpl(JdbcTemplate jdbcTemplate, DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Car> findAll() {
        String sql = "select car.id, car.code, car.make, car.model, car.year, car.notes,\n" +
                "                       bi.id, bi.hourly_rate, bi.materials_percentage, bi.insurance_rate,\n" +
                "                       bi.first_invoice, bi.first_invoice_mailed, bi.second_invoice, bi.second_invoice_mailed,\n" +
                "                       c.id as customer_id\n" +
                "                from car\n" +
                "                join billing_info bi on car.id = bi.car_id\n" +
                "                    join customer c on c.id = car.customer_id;";
        try {
            return jdbcTemplate.query(sql, new CarRowMapper());
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Optional<Car> findById(Integer id) {
        String sql = "select car.id, car.code, car.make, car.model, car.year, car.notes,\n" +
                "                       bi.id, bi.hourly_rate, bi.materials_percentage, bi.insurance_rate,\n" +
                "                       bi.first_invoice, bi.first_invoice_mailed, bi.second_invoice, bi.second_invoice_mailed,\n" +
                "                       c.id as customer_id\n" +
                "                from car\n" +
                "                join billing_info bi on car.id = bi.car_id\n" +
                "                    join customer c on c.id = car.customer_id\n" +
                "where car.id=?;";
        try {
            return jdbcTemplate.query(sql, new CarRowMapper(), id)
                    .stream()
                    .findFirst();
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Optional<Car> save(Integer customerId, Car car) {
        SqlParameterSource parameters = car.getNotes() == null ?
                new MapSqlParameterSource("customer_id", customerId)
                        .addValue("code", car.getCode())
                        .addValue("make", car.getMake())
                        .addValue("model", car.getModel())
                        .addValue("year", car.getYear()) :
                new MapSqlParameterSource("customer_id", customerId)
                        .addValue("code", car.getCode())
                        .addValue("make", car.getMake())
                        .addValue("model", car.getModel())
                        .addValue("year", car.getYear())
                        .addValue("notes", car.getNotes());

        BillingInfo billingInfo = car.getBillingInfo();
        String billingSql = "insert into billing_info (car_id, hourly_rate, materials_percentage, insurance_rate, first_invoice,\n" +
                "                          first_invoice_mailed, second_invoice, second_invoice_mailed)\n" +
                "values (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            Number car_id = simpleJdbcInsert.executeAndReturnKey(parameters);
            jdbcTemplate.update(billingSql, car_id.intValue(), billingInfo.getHourlyRate(), billingInfo.getMaterialsPercentage(), billingInfo.getInsuranceRate(),
                    billingInfo.getFirstInvoice(), false, billingInfo.getSecondInvoice(), false);
            return findById(car_id.intValue());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public Optional<Car> update(Car car) {
        String carSql = "update car\n" +
                "set code=?,\n" +
                "customer_id=?,\n" +
                "make=?,\n" +
                "model=?,\n"  +
                "year=?,\n" +
                "notes=?\n" +
                "where id=?;";
        String billingInfoSql = "update billing_info\n" +
                "set hourly_rate=?,\n" +
                "materials_percentage=?,\n" +
                "insurance_rate=?,\n" +
                "first_invoice=?,\n" +
                "first_invoice_mailed=?,\n" +
                "second_invoice=?,\n" +
                "second_invoice_mailed=?\n" +
                "where id=?;";
        try {
            BillingInfo billingInfo = car.getBillingInfo();
            jdbcTemplate.update(carSql, car.getCode(), car.getCustomerId(), car.getMake(), car.getModel(), car.getYear(), car.getNotes(), car.getId());
            jdbcTemplate.update(billingInfoSql, billingInfo.getHourlyRate(), billingInfo.getMaterialsPercentage(),
                    billingInfo.getInsuranceRate(), billingInfo.getFirstInvoice(), billingInfo.getFirstInvoiceMailed(),
                    billingInfo.getSecondInvoice(), billingInfo.getSecondInvoiceMailed(), billingInfo.getId());
            return findById(car.getId());
        } catch (DataAccessException e) {
            throw new IllegalStateException(INVALID_DATA_MESSAGE);
        }
    }

    @Override
    public List<Car> findByCustomerId(Integer customerId) {
        String sql = "select car.id," +
                "       car.code,\n" +
                "       car.make,\n" +
                "       car.model,\n" +
                "       car.year,\n" +
                "       car.notes,\n" +
                "       :customerId as customer_id,\n" +
                "       bi.id,\n" +
                "       bi.hourly_rate,\n" +
                "       bi.materials_percentage,\n" +
                "       bi.insurance_rate,\n" +
                "       bi.first_invoice,\n" +
                "       bi.first_invoice_mailed,\n" +
                "       bi.second_invoice,\n" +
                "       bi.second_invoice_mailed\n" +
                "from car join billing_info bi on car.id = bi.car_id\n" +
                "where customer_id=:customerId;";
        try {
            SqlParameterSource params = new MapSqlParameterSource("customerId", customerId);
            return namedParameterJdbcTemplate.query(sql, params, new CarRowMapper());
//            return jdbcTemplate.query(sql, new CarRowMapper(), customerId);
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

    @Override
    public Optional<Car> findByCode(String code) {
        String sql = "select car.id, car.code, car.make, car.model, car.year, car.notes,\n" +
                "       bi.id, bi.hourly_rate, bi.materials_percentage, bi.insurance_rate, \n" +
                "       bi.first_invoice, bi.first_invoice_mailed, bi.second_invoice, bi.second_invoice_mailed\n" +
                "from car\n" +
                "join billing_info bi on car.id = bi.car_id\n" +
                "where car.code=?;";
        try {
            return jdbcTemplate.query(sql, new CarRowMapper(), code)
                    .stream()
                    .findFirst();
        } catch (DataAccessException e) {
            throw new IllegalArgumentException(INVALID_DATA_MESSAGE);
        }
    }
}
