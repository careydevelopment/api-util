package us.careydevelopment.util.api.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RestResponse<T> implements IRestResponse<T> {

    private ResponseStatus responseStatus;
    private T response;
    
    @JsonIgnore
    private int httpStatusCode;

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }    
    
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
    
    @Override
    public T getResponse() {
        return response;
    }
    
    public void setResponse(T response) {
        this.response = response;
    }
    
    @Override
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
    
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
