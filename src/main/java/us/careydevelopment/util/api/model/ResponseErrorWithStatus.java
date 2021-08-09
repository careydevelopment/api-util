package us.careydevelopment.util.api.model;

public class ResponseErrorWithStatus implements ErrorResponse {

    private ResponseStatus responseStatus;
    private int statusCode;
    
    @Override
    public ResponseStatus getBody() {
        return responseStatus;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
