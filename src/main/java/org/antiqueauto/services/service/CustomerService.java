package org.antiqueauto.services.service;

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
            return customerDAO.save(customer)
                    .orElseThrow(() -> new CustomerNotFoundException(customer.getId()));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public List<Customer> findAll() {
        List<Customer> customers = customerDAO.findAll();
        customers.stream()
                .peek(customer -> customer.setCars(carDAO.findByCustomerId(customer.getId())))
                .collect(Collectors.toList());
        return customers;
    }

    public Customer findById(Integer customerId) {
        Customer customer = customerDAO.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        customer.setCars(carDAO.findByCustomerId(customerId));
        return customer;
    }

    public Customer update(Integer customerId, Customer updatedCustomer) {
        Customer customer = findById(customerId);
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());

        return customerDAO.update(customer).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Integer deleteById(Integer customerId) {
        Customer customer = findById(customerId);
        customerDAO.delete(customer.getId());
        return customerId;
    }
}
