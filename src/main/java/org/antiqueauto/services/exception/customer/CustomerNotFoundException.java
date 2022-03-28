package org.antiqueauto.services.exception.customer;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    private final UUID customerId;
    public CustomerNotFoundException(UUID customerId) {
        super(String.format("Customer %s not found", customerId));
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerNotFoundException{" +
                "customerId=" + customerId +
                '}';
    }
}
