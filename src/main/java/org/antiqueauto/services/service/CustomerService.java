package org.antiqueauto.services.service;

import org.antiqueauto.services.data.SampleData;
import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.exception.customer.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public Customer create(Customer customer) {
        try {
            Customer lastCustomer = SampleData.customers.get(SampleData.customers.size()-1);
            customer.setId(lastCustomer.getId()+1);
            SampleData.customers.add(customer);
            return customer;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<Customer> findAll() {
        return SampleData.customers;
    }

    public Customer findById(Long customerId) {
        return SampleData.customers
                .stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer update(Long customerId, Customer updatedCustomer) {
        if (!SampleData.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        Customer customer = findById(customerId);
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setCars(updatedCustomer.getCars());

        return customer;
    }
}
