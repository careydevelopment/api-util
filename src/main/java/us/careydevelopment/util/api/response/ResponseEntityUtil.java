package us.careydevelopment.util.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import us.careydevelopment.util.api.model.ErrorResponse;
import us.careydevelopment.util.api.model.ResponseErrorWithStatus;
import us.careydevelopment.util.api.model.ResponseStatus;
import us.careydevelopment.util.api.model.ResponseStatusCode;

public class ResponseEntityUtil {

    public static ResponseEntity<?> createResponseEntity(ErrorResponse errorResponse) {
        return ResponseEntity
                    .status(HttpStatus.valueOf(errorResponse.getStatusCode()))
                    .body(errorResponse.getBody());
    }
    
    
    public static ResponseEntity<?> createResponseEntityWithError(String message, int statusCode) {
        ResponseErrorWithStatus response = new ResponseErrorWithStatus();
        
        ResponseStatus status = new ResponseStatus();
        status.setMessage(message);
        status.setStatusCode(ResponseStatusCode.ERROR);
        
        response.setResponseStatus(status);
        response.setStatusCode(statusCode);
        
        return createResponseEntity(response);
    }
    
    
    public static ResponseEntity<?> createResponseEntityWithError(String message, HttpStatus httpStatus) {
        return createResponseEntityWithError(message, httpStatus.value());
    }
}
