package rs.payment.exchange.exception.service;


import rs.payment.exchange.exception.error.Error;

import rs.payment.exchange.exception.ApiException;
import rs.payment.exchange.exception.CustomException;


public class ApiServiceException extends ApiException {
    public ApiServiceException(Error error, CustomException cause) {
        super(error, cause);
    }
}
