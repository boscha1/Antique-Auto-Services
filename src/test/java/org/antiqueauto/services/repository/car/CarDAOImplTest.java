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
                true,
                new Date(2022, Calendar.APRIL, 15),
                true
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

    @Test
    @DisplayName("Should get one car by code")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void update() {
        Car car = objectUnderTest.findById(1).orElse(null);
        BillingInfo billingInfo = car.getBillingInfo();
        assert car != null;
        Assertions.assertEquals("SMI67", car.getCode());
        Assertions.assertEquals("ford", car.getMake());
        Assertions.assertEquals("mustang", car.getModel());
        Assertions.assertEquals(1967L, car.getYear());
        Assertions.assertNull(car.getNotes());
        Assertions.assertNotNull(billingInfo.getId());
        
        car.setCode("test");
        car.setMake("test");
        car.setModel("test");
        car.setYear(9999L);
        car.setNotes("test");
        billingInfo.setHourlyRate(99.9);
        billingInfo.setMaterialsPercentage(99.9);
        billingInfo.setInsuranceRate(99.9);
        billingInfo.setFirstInvoice(new Date(1, Calendar.JANUARY, 1));
        billingInfo.setFirstInvoiceMailed(true);
        billingInfo.setSecondInvoice(new Date(1, Calendar.JANUARY, 1));
        billingInfo.setSecondInvoiceMailed(true);

        Car result = objectUnderTest.update(car).orElse(null);
        
        Assertions.assertEquals("test", result.getCode());
        Assertions.assertEquals("test", result.getMake());
        Assertions.assertEquals("test", result.getModel());
        Assertions.assertEquals(9999L, result.getYear());
        Assertions.assertEquals("test", result.getNotes());
        Assertions.assertEquals(99.9, result.getBillingInfo().getHourlyRate());
        Assertions.assertEquals(99.9, result.getBillingInfo().getMaterialsPercentage());
        Assertions.assertEquals(99.9, result.getBillingInfo().getInsuranceRate());

    }
}