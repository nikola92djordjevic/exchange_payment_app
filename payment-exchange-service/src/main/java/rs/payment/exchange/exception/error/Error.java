package rs.payment.exchange.exception.error;


import java.sql.Timestamp;
import java.time.Instant;

public class Error {
    private String errorPosition;       //class name
    private Short statusCode;
    private String errorCode;
    private String errorFunction;       // method name
    private String errorClientText;
    private String errorOperatorText;
    private String errorAdminText;
    private Timestamp occurrenceTimestamp;

    public Error() {
    }

    public Error(String errorPosition, Short statusCode, String errorFunction, String description) {
        this.errorPosition = errorPosition;
        this.statusCode = statusCode;
        this.errorCode = String.valueOf(statusCode);
        this.errorFunction = errorFunction;
        this.errorClientText = description;
        this.errorOperatorText = description;
        this.errorAdminText = description;
        this.occurrenceTimestamp = Timestamp.from(Instant.now());
    }

    public Error(String errorPosition, Short statusCode, String errorCode,
                 String errorFunction, String description) {
        this.errorPosition = errorPosition;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorFunction = errorFunction;
        this.errorClientText = description;
        this.errorOperatorText = description;
        this.errorAdminText = description;
        this.occurrenceTimestamp = Timestamp.from(Instant.now());
    }

    public Error(String errorPosition, Short statusCode, String errorCode, String errorFunction,
                 String errorClientText, String errorOperatorText, String errorAdminText) {

        this.errorPosition = errorPosition;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorFunction = errorFunction;
        this.errorClientText = errorClientText;
        this.errorOperatorText = errorOperatorText;
        this.errorAdminText = errorAdminText;
        this.occurrenceTimestamp = Timestamp.from(Instant.now());
    }

    public Short getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorClientText() {
        return errorClientText;
    }

    public String getErrorAdminText() {
        return errorAdminText;
    }

}
