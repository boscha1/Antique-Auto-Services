package org.antiqueauto.services.data;

import org.antiqueauto.services.domain.BillingInfo;
import org.antiqueauto.services.domain.Car;
import org.antiqueauto.services.domain.Customer;

import java.util.*;

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
                UUID.fromString("1"),
                "anthony",
                "bosch",
                cars
        ));
        add(new Customer(
                UUID.fromString("2"),
                "joe",
                "smith",
                cars
        ));
        add(new Customer(
                UUID.fromString("3"),
                "jim",
                "smith",
                cars
        ));
    }};

    public static boolean existsById(UUID customerId) {
        return customers
                .stream()
                .anyMatch(customer -> customer.getCustomerId().equals(customerId));
    }
}

