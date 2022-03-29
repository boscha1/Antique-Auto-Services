package org.antiqueauto.services.controller;

import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/car")
    public List<Car> getAll() {
        return carService.findAll();
    }
}
