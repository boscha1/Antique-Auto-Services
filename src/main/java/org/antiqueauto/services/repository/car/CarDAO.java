package org.antiqueauto.services.repository.car;

import org.antiqueauto.services.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    List<Car> findAll();
    Optional<Car> findById(Integer id);
    Optional<Car> save(Integer customerId, Car car);
    List<Car> findByCustomerId(Integer customerId);
    void delete(Integer id);

    Optional<Car> findByCode(String code);
}
