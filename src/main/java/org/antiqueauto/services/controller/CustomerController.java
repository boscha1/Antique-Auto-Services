package org.antiqueauto.services.controller;

import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public Customer post(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAll() {
        return customerService.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getOne(@PathVariable UUID id) {
        return customerService.findById(id);
    }

    @PutMapping("/customer/{id}")
    public Customer put(@PathVariable UUID id, @RequestBody Customer customer) {
        return customerService.update(id, customer);
    }

    @DeleteMapping("/customer/{id}")
    public UUID delete(@PathVariable UUID id) {
        return customerService.deleteById(id);
    }

}
