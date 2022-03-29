package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> findAll();
    Optional<Customer> findById(Integer customerId);
    Customer save(Customer customer);
    Customer update(Customer customer);
    void delete(Integer id);
}
