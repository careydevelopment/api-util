package us.careydevelopment.util.api.response;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import us.careydevelopment.util.api.model.IRestResponse;
import us.careydevelopment.util.api.model.ResponseStatus;
import us.careydevelopment.util.api.model.ResponseStatusCode;
import us.careydevelopment.util.api.model.RestResponse;
import us.careydevelopment.util.api.model.ValidationError;

public class ResponseEntityUtilTest {

    @Test
    public void testCreateResponseEntity() {
        final int statusCode = 500;
        final String message = "Internal Server Error";
        
        final String statusMessage = "Something went wrong";
        final ResponseStatusCode responseStatusCode = ResponseStatusCode.ERROR;
               
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setMessage(statusMessage);
        responseStatus.setStatusCode(responseStatusCode);
        
        RestResponse<String> restResponse = new RestResponse<>();
        restResponse.setHttpStatusCode(statusCode);
        restResponse.setResponse(message);
        restResponse.setResponseStatus(responseStatus);
        
        ResponseEntity<IRestResponse<String>> re = ResponseEntityUtil.createResponseEntity(restResponse);
        Assertions.assertEquals(statusCode, re.getStatusCodeValue());

        IRestResponse<String> body = re.getBody();
        Assertions.assertEquals(message, body.getResponse());
        Assertions.assertEquals(statusMessage, body.getResponseStatus().getMessage());
        Assertions.assertEquals(responseStatusCode, body.getResponseStatus().getStatusCode());
    }
    
    @Test
    public void testCreateSuccessfulResponseEntityWithMessage() {
        final int statusCode = 200;
        final String message = "Success";
        final String statusMessage = "Successful POST";
        
        ResponseEntity<IRestResponse<String>> re = ResponseEntityUtil.createSuccessfulResponseEntity(message, statusCode, statusMessage);
        Assertions.assertEquals(statusCode, re.getStatusCodeValue());

        IRestResponse<String> body = re.getBody();
        Assertions.assertEquals(statusMessage, body.getResponse());
        Assertions.assertEquals(message, body.getResponseStatus().getMessage());
        Assertions.assertEquals(ResponseStatusCode.OK, body.getResponseStatus().getStatusCode());
    }
    
    @Test
    public void testCreateSuccessfulResponseEntityNoMessage() {
        final int statusCode = 200;
        final String message = "Success";
        
        ResponseEntity<IRestResponse<Void>> re = ResponseEntityUtil.createSuccessfulResponseEntity(message, statusCode);
        Assertions.assertEquals(statusCode, re.getStatusCodeValue());

        IRestResponse<Void> body = re.getBody();
        Assertions.assertEquals(message, body.getResponseStatus().getMessage());
        Assertions.assertEquals(ResponseStatusCode.OK, body.getResponseStatus().getStatusCode());
    }
    
    @Test
    public void testCreateResponseEntityWithErrorNoMessageIntStatus() {
        final int statusCode = 500;
        final String message = "Error";
        
        ResponseEntity<IRestResponse<Void>> re = ResponseEntityUtil.createResponseEntityWithError(message, statusCode);
        Assertions.assertEquals(statusCode, re.getStatusCodeValue());

        IRestResponse<Void> body = re.getBody();
        Assertions.assertEquals(message, body.getResponseStatus().getMessage());
        Assertions.assertEquals(ResponseStatusCode.ERROR, body.getResponseStatus().getStatusCode());
    }    
    
    @Test
    public void testCreateResponseEntityWithErrorNoMessageHttpStatus() {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final String message = "Error";
        
        ResponseEntity<IRestResponse<Void>> re = ResponseEntityUtil.createResponseEntityWithError(message, httpStatus);
        Assertions.assertEquals(httpStatus.value(), re.getStatusCodeValue());

        IRestResponse<Void> body = re.getBody();
        Assertions.assertEquals(message, body.getResponseStatus().getMessage());
        Assertions.assertEquals(ResponseStatusCode.ERROR, body.getResponseStatus().getStatusCode());
    }    
    
    @Test
    public void testCreateResponseEntityWithUnauthorized() {
        final int statusCode = 401;
        final String message = "Unauthorized";
        
        ResponseEntity<IRestResponse<Void>> re = ResponseEntityUtil.createResponseEntityWithUnauthorized(message);
        Assertions.assertEquals(statusCode, re.getStatusCodeValue());

        IRestResponse<Void> body = re.getBody();
        Assertions.assertEquals(message, body.getResponseStatus().getMessage());
        Assertions.assertEquals(ResponseStatusCode.UNAUTHORIZED, body.getResponseStatus().getStatusCode());
    }
    
    @Test
    public void testCreateResponseEntityWithValidationErrors() {
        final int statusCode = 400;
        
        final String defaultMessage = "defaultMessage";
        final String field = "field";

        final String statusMessage = "Validation error";
        final ResponseStatusCode responseStatusCode = ResponseStatusCode.ERROR;

        ValidationError error = new ValidationError();
        error.setDefaultMessage(defaultMessage);
        error.setField(field);
        
        List<ValidationError> errors = new ArrayList<>();
        errors.add(error);
                
        ResponseEntity<IRestResponse<List<ValidationError>>> re = ResponseEntityUtil.createResponseEntityWithValidationErrors(errors);
        Assertions.assertEquals(statusCode, re.getStatusCodeValue());

        IRestResponse<List<ValidationError>> body = re.getBody();
        Assertions.assertEquals(1, body.getResponse().size());
        Assertions.assertEquals(defaultMessage, body.getResponse().get(0).getDefaultMessage());
        Assertions.assertEquals(field, body.getResponse().get(0).getField());
        Assertions.assertEquals(statusMessage, body.getResponseStatus().getMessage());
        Assertions.assertEquals(responseStatusCode, body.getResponseStatus().getStatusCode());
    }
    
    
}
