package us.careydevelopment.util.api.model;

public interface IRestResponse<T> {

    ResponseStatus getResponseStatus();
    T getResponse();
    int getHttpStatusCode(); 
    
}
