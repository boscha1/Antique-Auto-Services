package org.antiqueauto.services.exception.car;

public class CarNotFoundException extends RuntimeException {
    private String code;
    private Integer id;
    public CarNotFoundException(String code) {
        super(String.format("Car %s not found", code));
        this.code = code;
    }

    public CarNotFoundException(Integer id) {
        super(String.format("Car %d not found", id));
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarNotFoundException{" +
                "code='" + code + '\'' +
                ", id=" + id +
                '}';
    }
}
