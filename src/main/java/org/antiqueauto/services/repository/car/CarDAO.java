package org.antiqueauto.services.repository.car;

import org.antiqueauto.services.domain.Car;

import java.util.List;

public interface CarDAO {
    List<Car> findAll();
    Car findById(Integer car);
    Car save(Car customer);

}
