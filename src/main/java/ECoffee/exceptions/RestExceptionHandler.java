package ECoffee.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Default Handler. catch-all type of logic that deals with all other exceptions that don't have specific handlers
     *
     * @param ex      Exception
     * @param request WebRequest
     * @return the ApiError object
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        ex.printStackTrace();
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Service exception
     *
     * @param request   WebRequest
     * @param exception de type FunctionalException
     * @return ResponseEntity<String> -> JSON basé sur la classe ApiError
     * @see ApiError
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> serviceException(WebRequest request, ServiceException exception) throws JsonProcessingException {

        ApiError apiError = new ApiError(exception.getHttpStatus(), exception.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}