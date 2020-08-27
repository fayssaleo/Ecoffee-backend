package ECoffee.exceptions;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.http.HttpStatus;
import java.io.Serializable;


public class ApiError implements Serializable {

    private static final long serialVersionUID = -3443462488291535473L;

    private HttpStatus status;
    private int code;

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;


    public ApiError(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }

    public ApiError(HttpStatus httpStatus, String message) {
        this.message = message;
        this.status = httpStatus;
        this.code = status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }




}