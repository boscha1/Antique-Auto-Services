package org.antiqueauto.services.domain;

public class Car {
    private final Long id;
    private String make;
    private String model;
    private Long year;
    private String notes;

    public Car(Long id, String make, String model, Long year, String notes) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
