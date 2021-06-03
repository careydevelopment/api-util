package us.careydevelopment.util.api.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import us.careydevelopment.util.api.model.ResponseStatus;
import us.careydevelopment.util.api.model.ResponseStatusCode;


public class ResponseWriterUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseWriterUtil.class);
	
    public static void writeResponse(HttpServletResponse response, String message) {
        try (PrintWriter writer = response.getWriter()) {
            writer.write(message);
            writer.flush();
        } catch (IOException ie) {
            LOG.error("Problem writing output to response!", ie);
        }
    }
	
	
    public static void writeErrorResponse(HttpServletResponse response, String message) {
        ResponseStatus status = new ResponseStatus();
        status.setStatusCode(ResponseStatusCode.ERROR);
        status.setMessage(message);

        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        
        try (PrintWriter writer = response.getWriter()) {
            String json = new ObjectMapper().writeValueAsString(status);
			
            writer.write(json);
            writer.flush();
        } catch (IOException ie) {
            LOG.error("Problem writing output to response!", ie);
        }
    }
    
    
    public static void writeResponse(HttpServletResponse response, String message, ResponseStatusCode statusCode) {
        ResponseStatus status = new ResponseStatus();
        status.setStatusCode(statusCode);
        status.setMessage(message);
                
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        
        try (PrintWriter writer = response.getWriter()) {
            String json = new ObjectMapper().writeValueAsString(status);
                        
            writer.write(json);
            writer.flush();
        } catch (IOException ie) {
            LOG.error("Problem writing output to response!", ie);
        }
    }
}
