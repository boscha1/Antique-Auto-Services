package org.antiqueauto.services.service;

import org.antiqueauto.services.data.SampleData;
import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.exception.customer.CustomerNotFoundException;
import org.antiqueauto.services.repository.car.CarDAO;
import org.antiqueauto.services.repository.customer.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CarDAO carDAO;


    public Customer create(Customer customer) {
        try {
            // SampleData.customers.add(customer);
            return customerDAO.save(customer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<Customer> findAll() {
        // return SampleData.customers;
        List<Customer> customers = customerDAO.findAll();
        customers.stream()
                .peek(customer -> customer.setCars(carDAO.findByCustomerId(customer.getCustomerId())))
                .collect(Collectors.toList());
        return customers;
    }

    public Customer findById(Integer customerId) {
        return SampleData.customers
                .stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer update(Integer customerId, Customer updatedCustomer) {
        if (!SampleData.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        Customer customer = findById(customerId);
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setCars(updatedCustomer.getCars());

        return customer;
    }

    public Integer deleteById(Integer customerId) {
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
