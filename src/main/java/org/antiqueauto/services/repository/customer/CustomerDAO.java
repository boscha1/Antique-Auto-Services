package org.antiqueauto.services.repository.customer;

import org.antiqueauto.services.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> findAll();
    Optional<Customer> findById(Integer customerId);
    Optional<Customer> save(Customer customer);
    Optional<Customer> update(Customer customer);
    boolean existsById(Integer id);
    void delete(Integer id);
}
