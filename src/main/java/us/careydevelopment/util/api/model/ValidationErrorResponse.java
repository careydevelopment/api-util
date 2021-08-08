package us.careydevelopment.util.api.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ValidationErrorResponse implements ErrorResponse {
    
    private static final int STATUS_CODE = 400;
    
    private List<ValidationError> errors = new ArrayList<ValidationError>();
    
    public List<ValidationError> getErrors() {
        return errors;
    }
    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }    
    
    public boolean hasErrors() {
        return (errors != null && errors.size() > 0);
    }
    
    @Override
    public List<ValidationError> getBody() {
        return errors;
    }    
    
    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
