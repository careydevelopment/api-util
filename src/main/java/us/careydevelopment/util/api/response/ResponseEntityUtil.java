package us.careydevelopment.util.api.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import us.careydevelopment.util.api.model.IRestResponse;
import us.careydevelopment.util.api.model.ResponseStatus;
import us.careydevelopment.util.api.model.ResponseStatusCode;
import us.careydevelopment.util.api.model.RestResponse;
import us.careydevelopment.util.api.model.ValidationError;

public class ResponseEntityUtil {

    public static <T> ResponseEntity<IRestResponse<T>> createResponseEntity(IRestResponse<T> response) {
        return ResponseEntity
                    .status(HttpStatus.valueOf(response.getHttpStatusCode()))
                    .body(response);
    }
    
    public static <T> ResponseEntity<IRestResponse<T>> createSuccessfulResponseEntity(String message, int httpStatusCode, T response) {
        ResponseStatus status = getSuccessResponseStatus(message);
        
        RestResponse<T> fullResponse = new RestResponse<>();
        fullResponse.setResponseStatus(status);
        fullResponse.setHttpStatusCode(httpStatusCode);
        fullResponse.setResponse(response);
        
        return createResponseEntity(fullResponse);
    }
    
    public static ResponseEntity<IRestResponse<Void>> createResponseEntityWithError(String message, int httpStatusCode) {
        ResponseStatus status = getErrorResponseStatus(message);
        
        RestResponse<Void> fullResponse = new RestResponse<>();
        fullResponse.setResponseStatus(status);
        fullResponse.setHttpStatusCode(httpStatusCode);
        
        return createResponseEntity(fullResponse);
    }
    
    public static ResponseEntity<?> createResponseEntityWithError(String message, HttpStatus httpStatus) {
        return createResponseEntityWithError(message, httpStatus.value());
    }
    
    public static ResponseEntity<IRestResponse<List<ValidationError>>> createResponseEntityWithValidationErrors(List<ValidationError> errors) {
        RestResponse<List<ValidationError>> fullResponse = new RestResponse<>();
        
        if (errors != null && errors.size() > 0) {
            ResponseStatus responseStatus = getErrorResponseStatus("Validation error");
            
            fullResponse.setResponse(errors);
            fullResponse.setResponseStatus(responseStatus);
            fullResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());            
        }
        
        return createResponseEntity(fullResponse);
    }
    
    private static ResponseStatus getErrorResponseStatus(String message) {
        ResponseStatus status = new ResponseStatus();
        status.setMessage(message);
        status.setStatusCode(ResponseStatusCode.ERROR);
        
        return status;
    }
    
    
    private static ResponseStatus getSuccessResponseStatus(String message) {
        ResponseStatus status = new ResponseStatus();
        status.setMessage(message);
        status.setStatusCode(ResponseStatusCode.OK);
        
        return status;
    }
}
