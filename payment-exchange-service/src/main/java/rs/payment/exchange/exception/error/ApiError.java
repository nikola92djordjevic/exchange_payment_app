package rs.payment.exchange.exception.error;

import java.util.List;

public class ApiError {
    private final int responseCode;
    private String errorCode;

    public ApiError(List<Error> errors) {
        this.responseCode = errors.get(0).getStatusCode();
        this.errorCode = errors.get(0).getErrorCode();
        String errorMessage = errors.get(0).getErrorAdminText();

        setErrors(errors);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrors(List<Error> errors) {
        if(errors.size() == 0) {
            throw new IllegalArgumentException("List of errors must not be empty");
        }
    }
}
