package rs.payment.exchange.exception;


import rs.payment.exchange.exception.error.Error;

public class ApiException extends CustomException {

    public ApiException(Error error, CustomException cause) {
        super(error, cause);
    }

}
