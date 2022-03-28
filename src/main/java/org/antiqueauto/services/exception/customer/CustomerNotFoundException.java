package org.antiqueauto.services.exception.customer;

public class CustomerNotFoundException extends RuntimeException {
    private final Long customerId;
    public CustomerNotFoundException(Long customerId) {
        super(String.format("Customer %d not found", customerId));
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerNotFoundException{" +
                "customerId=" + customerId +
                '}';
    }
}
