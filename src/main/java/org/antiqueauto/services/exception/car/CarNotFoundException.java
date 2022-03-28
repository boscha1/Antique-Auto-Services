package org.antiqueauto.services.exception.car;

public class CarNotFoundException extends RuntimeException {
    private final String code;
    public CarNotFoundException(String code) {
        super(String.format("Car %s not found", code));
        this.code = code;
    }

    @Override
    public String toString() {
        return "CarNotFoundException{" +
                "code='" + code + '\'' +
                '}';
    }
}
