package org.antiqueauto.services.repository.customer;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.antiqueauto.services.AntiqueAutoApplication;
import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.exception.customer.CustomerNotFoundException;
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

import java.util.List;


@ExtendWith(SpringExtension.class)
@ActiveProfiles({"local"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@SpringBootTest(classes = {
        CustomerDAOImpl.class, AntiqueAutoApplication.class
})
@Transactional
class CustomerDAOImplTest {

    @Autowired
    private CustomerDAOImpl objectUnderTest;

    @Test
    @DisplayName("Should get all customers")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findAll() {
        List<Customer> result = objectUnderTest.findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should get one customer by Id")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findById() {
        Customer result = objectUnderTest.findById(1)
                .orElseThrow(() -> new CustomerNotFoundException(1));
        Assertions.assertEquals(result.getId(), 1);
        Assertions.assertEquals(result.getFirstName(), "joe");
        Assertions.assertEquals(result.getLastName(), "smith");
    }

    @Test
    @DisplayName("Should save one customer")
    @DatabaseSetup("/org/antiqueauto/services/repository/empty-customer-setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/empty-customer-setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void save() {
        Customer customer = new Customer(null, "test", "test", null);
        Customer result = objectUnderTest.save(customer).orElse(null);
        assert result != null;
        Assertions.assertNotNull(result.getId());
    }

    @Test
    @DisplayName("Should update one customer by Id")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void update() {
        Customer customer = objectUnderTest.findById(1).orElse(null);
        assert customer != null;
        Assertions.assertEquals("joe", customer.getFirstName());
        Assertions.assertEquals("smith", customer.getLastName());

        customer.setFirstName("test");
        customer.setLastName("test");

        Customer result = objectUnderTest.update(customer).orElse(null);
        assert result != null;
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("test", result.getFirstName());
        Assertions.assertEquals("test", result.getLastName());
    }

    @Test
    @DisplayName("Should delete one customer and all child data")
    @DatabaseSetup("/org/antiqueauto/services/repository/setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void delete() {
        objectUnderTest.delete(1);
        Customer customer = objectUnderTest.findById(1)
                .orElse(null);
        Assertions.assertNull(customer);
    }
}