package com.cafepal.order.exception;

public class VoucherNotFoundException extends RuntimeException {
    public VoucherNotFoundException(String message) {
        super(message);
    }
}
