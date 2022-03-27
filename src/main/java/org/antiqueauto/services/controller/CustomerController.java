package org.antiqueauto.services.controller;

import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Customer post(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/")
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getOne(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public Customer updateOne(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.update(id, customer);
    }

}
