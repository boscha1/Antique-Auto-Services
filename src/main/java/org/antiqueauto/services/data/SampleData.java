package org.antiqueauto.services.data;

import org.antiqueauto.services.domain.BillingInfo;
import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.domain.Customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SampleData {
    public static final List<Car> cars = new ArrayList<>() {{
        add(new Car(
                "toyota",
                "highlander",
                2000L,
                "these are additional notes about the car",
                new BillingInfo(
                        20.0,
                        .10,
                        20.0,
                        new Date(2022, Calendar.APRIL, 1),
                        new Date(2022, Calendar.APRIL, 15)
                )
        ));
        add(new Car(
                "toyota",
                "highlander",
                1990L,
                "these are additional notes about the car",
                new BillingInfo(
                        20.0,
                        .10,
                        20.0,
                        new Date(2022, Calendar.APRIL, 1),
                        new Date(2022, Calendar.APRIL, 15)
                )
        ));
    }};
    public static final List<Customer> customers = new ArrayList<>() {{
        add(new Customer(
                1L,
                "anthony",
                "bosch",
                cars
        ));
        add(new Customer(
                2L,
                "joe",
                "smith",
                cars
        ));
        add(new Customer(
                3L,
                "jim",
                "smith",
                cars
        ));
    }};

    public static boolean existsById(Long customerId) {
        return customers
                .stream()
                .anyMatch(customer -> customer.getId().equals(customerId));
    }
}

