package us.careydevelopment.util.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.careydevelopment.util.api.model.ValidationError;

public class InputSanitizerTest {

    @Test
    public void inputSanitizerHappyPathTest() {
        ValidationError error = new ValidationError();
        error.setCode("code");
        error.setField("field*^%");
        
        InputSanitizer.sanitizeBasic(error);
        
        Assertions.assertEquals("field", error.getField());
        Assertions.assertEquals("code", error.getCode());
    }
}
