package org.antiqueauto.services.domain;

public class Car {
    private Integer id;
    private String code;
    private String make;
    private String model;
    private Long year;
    private String notes;
    private Integer customerId;
    private BillingInfo billingInfo;

    public Car() {

    }

    public Car(String code, String make, String model, Long year, String notes, BillingInfo billingInfo) {
        this.code = code;
        this.make = make;
        this.model = model;
        this.year = year;
        this.notes = notes;
        this.billingInfo = billingInfo;
    }

    public Car(Integer id, String code, String make, String model, long year, String notes, Integer customerId, BillingInfo billingInfo) {
        this.id = id;
        this.code = code;
        this.make = make;
        this.model = model;
        this.year = year;
        this.notes = notes;
        this.customerId = customerId;
        this.billingInfo = billingInfo;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public Integer getCustomerId() {
        return customerId;
    }
}
