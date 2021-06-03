package us.careydevelopment.util.api.model;

public class ResponseStatus {
	
    private ResponseStatusCode statusCode;
    private String message;
	
    public ResponseStatusCode getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(ResponseStatusCode statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
