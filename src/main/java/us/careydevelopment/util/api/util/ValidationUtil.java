package us.careydevelopment.util.api.util;

import java.util.Set;

import javax.validation.ConstraintViolation;

import us.careydevelopment.util.api.model.ValidationError;
import us.careydevelopment.util.api.model.ValidationErrorResponse;


public class ValidationUtil {

    public static ValidationErrorResponse convertConstraintViolationsToValidationErroResponse(Set<ConstraintViolation<Object>> violations) {
        ValidationErrorResponse response = new ValidationErrorResponse();
        
        if (violations != null) {
            violations.forEach(violation -> {
                String message = violation.getMessage();
                String field = violation.getPropertyPath().toString();
                
                ValidationError error = new ValidationError();
                error.setCode(field);
                error.setDefaultMessage(message);
                error.setField(field);
                
                response.getErrors().add(error);
            });
        }
        
        return response;
    }
}
