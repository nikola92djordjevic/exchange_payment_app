package rs.payment.exchange.exception.error;

import org.springframework.http.HttpStatus;

public class ErrorCode {
    public static final Short NO_CONTENT_CODE = (short) HttpStatus.NO_CONTENT.value();
    public static final Short BAD_REQUEST_CODE = (short) HttpStatus.BAD_REQUEST.value();
    public static final Short UNAUTHORIZED_CODE = (short) HttpStatus.UNAUTHORIZED.value();
    public static final Short FORBIDDEN_CODE = (short) HttpStatus.UNAUTHORIZED.value();
    public static final Short NOT_FOUND_CODE = (short) HttpStatus.NOT_FOUND.value();
    public static final Short CONFLICT_CODE = (short) HttpStatus.CONFLICT.value();
    public static final Short INTERNAL_SERVER_ERROR_CODE = (short) HttpStatus.INTERNAL_SERVER_ERROR.value();
}
