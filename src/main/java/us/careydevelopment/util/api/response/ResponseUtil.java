package us.careydevelopment.util.api.response;

import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import us.careydevelopment.util.api.model.ResponseStatusCode;

public class ResponseUtil {
    
    private static final String TOO_MANY_FAILED_LOGINS = "Too many failed login attempts. Please try again tomorrow.";
    private static final String UNAUTHORIZED_ORIGIN = "You're not allowed to access this resource from that origin.";
    private static final String TOO_MANY_FAILED_IP_LOGINS = "Your IP address has failed authentication too many times today.";
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    
    
    public static void tooManyFailedLogins(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
        sendResponse(response, TOO_MANY_FAILED_LOGINS);
    }
    
    
    public static void invalidCredentials(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
        sendResponse(response, INVALID_CREDENTIALS);
    }
    
    
    public static void tooManyFailedIpLogins(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
        sendResponse(response, TOO_MANY_FAILED_IP_LOGINS);
    }
    
    
    public static void badOrigin(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
        sendResponse(response, UNAUTHORIZED_ORIGIN);
    }
    
    
    private static void sendResponse(HttpServletResponse response, String message) {
        ResponseStatusCode responseStatusCode = getStatusCodeFromResponse(response);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        
        ResponseWriterUtil.writeResponse(response, message, responseStatusCode);
    }
    
    
    private static ResponseStatusCode getStatusCodeFromResponse(HttpServletResponse response) {
        ResponseStatusCode statusCode = ResponseStatusCode.OK;
        
        if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED || 
            response.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
            statusCode = ResponseStatusCode.UNAUTHORIZED;
        } else if (response.getStatus() > 399) {
            statusCode = ResponseStatusCode.ERROR;
        }
        
        return statusCode;
    }
}
