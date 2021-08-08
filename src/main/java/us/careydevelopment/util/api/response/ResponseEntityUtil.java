package us.careydevelopment.util.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import us.careydevelopment.util.api.model.ErrorResponse;

public class ResponseEntityUtil {

    public static ResponseEntity<?> createResponseEntity(ErrorResponse errorResponse) {
        return ResponseEntity
                    .status(HttpStatus.valueOf(errorResponse.getStatusCode()))
                    .body(errorResponse.getBody());
    }
}
