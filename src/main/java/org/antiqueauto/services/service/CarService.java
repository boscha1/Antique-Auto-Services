package org.antiqueauto.services.service;

import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.repository.car.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarDAO carDAO;

    public List<Car> findAll() {
        return carDAO.findAll();
    }
}
