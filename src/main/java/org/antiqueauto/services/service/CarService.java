package org.antiqueauto.services.service;

import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.domain.Customer;
import org.antiqueauto.services.exception.car.CarNotFoundException;
import org.antiqueauto.services.exception.customer.CustomerNotFoundException;
import org.antiqueauto.services.repository.car.CarDAO;
import org.antiqueauto.services.repository.customer.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CarService {
    @Autowired
    private CarDAO carDAO;

    @Autowired
    private CustomerDAO customerDAO;

    public Car create(Integer customerId, Car car) {
        try {
            Customer customer = customerDAO.findById(customerId)
                    .orElseThrow(() -> new CustomerNotFoundException(customerId));
            car.setCode(generateCode(customer.getLastName(), car.getYear()));
            carDAO.save(customer.getId(), car);
            return car;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public Car update(Integer id, Car car) {
        return carDAO.update(id, car)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public List<Car> findAll() {
        return carDAO.findAll();
    }

    public Car findById(Integer id) {
        return carDAO.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public Car findByCode(String code) {
        return carDAO.findByCode(code)
                .orElseThrow(() -> new CarNotFoundException(code));
    }

    private String generateCode(String name, Long year) {
        return name.substring(0, Math.min(name.length(), 3)).toUpperCase(Locale.ROOT) + year.toString().substring(2,4);
    }
}
