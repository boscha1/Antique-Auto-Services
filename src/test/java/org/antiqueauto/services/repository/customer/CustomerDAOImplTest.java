package org.antiqueauto.services.repository.customer;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.antiqueauto.services.AntiqueAutoApplication;
import org.antiqueauto.services.domain.Customer;
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
    @DatabaseSetup("/org/antiqueauto/services/repository/customer-setup.xml")
    @DatabaseTearDown(
            value = "/org/antiqueauto/services/repository/customer-setup.xml",
            type = DatabaseOperation.DELETE_ALL
    )
    void findAll() {
        List<Customer> result = objectUnderTest.findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}