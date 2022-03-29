package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(Integer customerId);
    Customer save(Customer customer);
}
