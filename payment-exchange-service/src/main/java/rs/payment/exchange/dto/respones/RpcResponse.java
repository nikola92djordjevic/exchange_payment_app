package rs.payment.exchange.dto.respones;

import org.springframework.http.HttpStatus;
import rs.payment.exchange.exception.error.Error;

import java.util.ArrayList;
import java.util.List;

public class RpcResponse {

    private Integer responseCode;
    private String responseMessage;
    private List<Error> errors;

    public RpcResponse() {
    }

    public void addError(Error error) {
        if (errors == null) {
            responseCode = (int) error.getStatusCode();
            responseMessage = error.getErrorClientText();
            errors = new ArrayList<>();
        }

        errors.add(error);
    }

    public void setOk(){
        this.responseCode =HttpStatus.OK.value();
        this.responseMessage = "OK";
    }

    public void setNotFound(){
        this.responseCode =HttpStatus.NOT_FOUND.value();
        this.responseMessage = "NOT_FOUND";
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
