package rs.payment.exchange.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import rs.payment.exchange.exception.service.ApiServiceException;
import rs.payment.exchange.exception.error.ApiError;

@Order(1)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return ResponseEntity.status(apiError.getResponseCode()).body(apiError);
    }


    @ExceptionHandler(ApiServiceException.class)
    protected ResponseEntity<ApiError> handleServiceException(ApiServiceException exception) {
        ApiError errorResponse = new ApiError(exception.getErrorStack());
        return buildResponseEntity(errorResponse);
    }

}
