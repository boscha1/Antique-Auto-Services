package org.antiqueauto.services.service;

import org.antiqueauto.services.data.SampleData;
import org.antiqueauto.services.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public List<Customer> getCustomers() {
        return SampleData.customers;
    }
}
