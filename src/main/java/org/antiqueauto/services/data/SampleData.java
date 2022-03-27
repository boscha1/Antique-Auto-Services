package org.antiqueauto.services.data;

import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static final List<Car> cars = new ArrayList<>() {{
        add(new Car(
                1L,
                "toyota",
                "highlander",
                2000L,
                "these are additional notes about the car"
        ));
    }};
    public static final List<Customer> customers = new ArrayList<>() {{
        add(new Customer(
                1L,
                "anthony",
                "bosch",
                cars
        ));
    }};
}

