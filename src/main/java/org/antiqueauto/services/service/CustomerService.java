package org.antiqueauto.services.service;

import org.antiqueauto.services.data.SampleData;
import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.exception.customer.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    public Customer create(Customer customer) {
        try {
            SampleData.customers.add(customer);
            return customer;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<Customer> findAll() {
        return SampleData.customers;
    }

    public Customer findById(UUID customerId) {
        return SampleData.customers
                .stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer update(UUID customerId, Customer updatedCustomer) {
        if (!SampleData.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        Customer customer = findById(customerId);
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setCars(updatedCustomer.getCars());

        return customer;
    }

    public UUID deleteById(UUID customerId) {
        if (!SampleData.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }

        try {
            SampleData.customers.remove(findById(customerId));
            return customerId;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
