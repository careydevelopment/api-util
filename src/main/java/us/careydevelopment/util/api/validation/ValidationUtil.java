package us.careydevelopment.util.api.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

import us.careydevelopment.util.api.model.ValidationError;


public class ValidationUtil {
   
    public static List<ValidationError> convertBindingResultToValidationErrors(BindingResult bindingResult) {
        List<ValidationError> errors = new ArrayList<>();
        
        if (bindingResult != null) {
            bindingResult.getFieldErrors().forEach(violation -> {
                String message = violation.getDefaultMessage();
                String field = violation.getField();
                
                ValidationError error = new ValidationError();
                //error.setCode(field);
                error.setDefaultMessage(message);
                error.setField(field);
                
                errors.add(error);
            });
        }
        
        return errors;
    }
}
