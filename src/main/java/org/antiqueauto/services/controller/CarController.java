package org.antiqueauto.services.controller;

import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car/{customerId}")
    public Car post(@PathVariable Integer customerId, @RequestBody Car car) {
        return carService.create(customerId, car);
    }

    @PutMapping("/car/{id}")
    public Car put(@PathVariable Integer id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @GetMapping("/car")
    public List<Car> getAll() {
        List<Car> cars = carService.findAll();
        return cars;
    }

    @GetMapping("/car/{id}")
    public Car getOneById(@PathVariable Integer id) {
        return carService.findById(id);
    }

    @GetMapping("/car/code/{code}")
    public Car getOneByCode(@PathVariable String code) {
        return carService.findByCode(code);
    }
}
