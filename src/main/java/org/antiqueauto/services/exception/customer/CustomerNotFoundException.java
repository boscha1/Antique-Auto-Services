package org.antiqueauto.services.exception.customer;

public class CustomerNotFoundException extends RuntimeException {
    private final Integer customerId;
    public CustomerNotFoundException(Integer customerId) {
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
