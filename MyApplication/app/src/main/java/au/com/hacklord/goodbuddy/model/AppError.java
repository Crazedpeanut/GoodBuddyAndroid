package au.com.hacklord.goodbuddy.model;

/**
 * Created by john on 27/08/2016.
 */
public class AppError {
    public enum ErrorType {
        UI,
        AUTH
    }

    private String message;
    ErrorType errorType;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
