package us.careydevelopment.util.api.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ValidationError {

    private String field;
    private String code;
    private String defaultMessage;
    
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDefaultMessage() {
        return defaultMessage;
    }
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }    
}
