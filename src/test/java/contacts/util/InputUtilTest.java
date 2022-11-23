package contacts.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputUtilTest {
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void getInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Called\n".getBytes());
        System.setIn(inputStream);

        String expected = "Called";
        assertEquals(expected, InputUtil.getInput("Message to print on same line"));
        assertEquals("Message to print on same line", outputStream.toString());
    }
}