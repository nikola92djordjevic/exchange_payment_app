package rs.payment.exchange.exception;

import rs.payment.exchange.exception.error.Error;
import java.util.ArrayList;
import java.util.List;

public class CustomException extends RuntimeException { //TODO : return this to Exception after refactoring of Feign throws part

    private Error error;

    public CustomException(Error error, CustomException cause) {
        super(error.getErrorAdminText(), cause);
        this.error = error;
    }

    public List<Error> getErrorStack(){
        List<Error> errors = new ArrayList<>();
        errors.add(getError());

        Throwable cause = getCause();
        while (cause instanceof CustomException) {
            errors.add(((CustomException) cause).getError());
            cause = cause.getCause();
        }

        return errors;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
