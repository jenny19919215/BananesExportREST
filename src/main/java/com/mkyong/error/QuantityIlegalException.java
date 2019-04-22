package com.mkyong.error;

public class QuantityIlegalException extends RuntimeException {

    public QuantityIlegalException(Integer q) {
        super("Quantity should be 25* N but not " + q);
    }

}
