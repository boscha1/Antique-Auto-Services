package org.antiqueauto.services.service;

import org.antiqueauto.services.data.SampleData;
import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public List<Customer> findAll() {
        return SampleData.customers;
    }

    public Customer findById(Long customerId) {
        return SampleData.customers
                .stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer %d not found", customerId)));
    }
}
