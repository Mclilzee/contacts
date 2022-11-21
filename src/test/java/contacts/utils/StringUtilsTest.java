package contacts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isValidPhoneNumber() {
        assertTrue(StringUtils.isValidPhoneNumber("+0 (123) 456-789-ABcd"));
    }
}