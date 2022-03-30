package org.antiqueauto.services.repository.car;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.antiqueauto.services.AntiqueAutoApplication;
import org.antiqueauto.services.domain.BillingInfo;
import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.exception.car.CarNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@SpringBootTest(classes = {
        CarDAOImpl.class, AntiqueAutoApplication.class
})
@Transactional
class CarDAOImplTest {

    @Autowired
    private CarDAOImpl objectUnderTest;

    @Test
    @DisplayName("Should get all cars")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findAll() {
        List<Car> result = objectUnderTest.findAll();
        Assertions.assertEquals(4, result.size());
    }

    @Test
    @DisplayName("Should get one car by Id")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findById() {
        Car result = objectUnderTest.findById(1)
                .orElseThrow(() -> new CarNotFoundException(1));
        Assertions.assertEquals("SMI67", result.getCode());
        Assertions.assertEquals("ford", result.getMake());
        Assertions.assertEquals("mustang", result.getModel());
        Assertions.assertEquals(1967, result.getYear());
        Assertions.assertNull(result.getNotes());
    }

    @Test
    @DisplayName("Should save one car")
    @DatabaseSetup("/org/antiqueauto/services/repository/empty-car-setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/empty-car-setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void save() {
        BillingInfo billingInfo = new BillingInfo(
                20.0,
                .10,
                20.0,
                new Date(2022, Calendar.APRIL, 1),
                new Date(2022, Calendar.APRIL, 15)
        );
        Car car = new Car("testCode", "testMake", "testModel", 9999L, "testNotes", billingInfo);

        Car result = objectUnderTest.save(1, car).orElse(null);

        assert result != null;
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getBillingInfo().getId());
    }

    @Test
    @DisplayName("Should find one car by customer Id")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findByCustomerId() {
        List<Car> result = objectUnderTest.findByCustomerId(1);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should delete one car by Id")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void delete() {
        objectUnderTest.delete(1);
        Car car = objectUnderTest.findById(1)
                .orElse(null);
        Assertions.assertNull(car);
    }

    @Test
    @DisplayName("Should get one car by code")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findByCode() {
        Car result = objectUnderTest.findByCode("SMI67").orElse(null);

        assert result != null;
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("ford", result.getMake());
        Assertions.assertEquals("mustang", result.getModel());
        Assertions.assertEquals(1967L, result.getYear());
        Assertions.assertNull(result.getNotes());
    }
}