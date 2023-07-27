package com.example.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class InsuffientAmountException extends ResponseStatusException {
    public InsuffientAmountException() {
        super(HttpStatus.BAD_REQUEST,"Insuffient Amount!");
    }
}
