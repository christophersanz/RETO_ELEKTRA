package com.customer.exceptions;

import org.springframework.http.HttpStatus;

public class GenericClientException extends RuntimeException {

    private HttpStatus status;

    public GenericClientException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
