package org.antiqueauto.services.domain;

import java.util.List;
import java.util.UUID;

public class Customer {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private List<Car> cars;

    public Customer(UUID id, String firstName, String lastName, List<Car> cars) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = cars;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
