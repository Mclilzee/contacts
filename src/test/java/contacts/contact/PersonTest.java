package contacts.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person = new Person("John", "Doe", "1992-2-3", "M");

    private final static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        System.setOut(new PrintStream(outputStream));
    }

    @BeforeEach
    void setup() {
        outputStream.reset();
    }

    @Test
    void returnCorrectName() {
        String expected = "John";
        assertEquals(expected, person.getName());
    }

    @Test
    void returnCorrectSurname() {
        String expected = "Doe";
        assertEquals(expected, person.getSurname());
    }

    @Test
    void returnCorrectMaleGender() {
        String expected = "M";
        assertEquals(expected, person.getGender());
    }

    @ParameterizedTest
    @MethodSource("correctGenderProvider")
    void initWithCorrectGender(String gender) {
        String expected = gender.toUpperCase();
        person = new Person("John", "Doe", "1992-2-3", gender);
        assertEquals(expected, person.getGender());
        assertEquals("", outputStream.toString());
    }

    @ParameterizedTest
    @MethodSource("correctGenderProvider")
    void setCorrectGender(String gender) {
        person = new Person("John", "Doe", "1992-2-3", "F");
        person.initGender(gender);
        assertEquals(gender.toUpperCase(), person.getGender());
        assertEquals("", outputStream.toString());

    }

    @ParameterizedTest
    @MethodSource("wrongGenderProvider")
    void initWithWrongGender(String gender) {
        person = new Person("John", "Doe", "1993-1-1", gender);
        String expected = "[no data]";
        assertEquals(expected, person.getGender());

        String expectedOutput = "Bad gender!\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @ParameterizedTest
    @MethodSource("wrongGenderProvider")
    void setWrongGender(String gender) {
        person = new Person("John", "Doe", "1993-1-1", "F");
        person.setGender(gender);
        String expected = gender == null ? "" : gender.toUpperCase();
        assertEquals(expected, person.getGender());

        String expectedOutput = "Bad gender!\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void returnCorrectBirthdate() {
        String expected = "1992-2-3";
        assertEquals(expected, person.getBirthDate());
    }

    @ParameterizedTest
    @MethodSource("correctDateProvider")
    void initWithCorrectDates(String date) {
        person = new Person("John", "Doe", date, "F");
        assertEquals(date, person.getBirthDate());
        assertEquals("", outputStream.toString());
    }

    @ParameterizedTest
    @MethodSource("correctDateProvider")
    void setCorrectBirthDate(String date) {
        person.setBirthDate(date);
        assertEquals(date, person.getBirthDate());
        assertEquals("", outputStream.toString());
    }

    @ParameterizedTest
    @MethodSource("wrongDateProvider")
    void initWithWrongBirthDate(String date) {
        person = new Person("John", "Doe", date, "F");
        String expected = "[no data]";
        assertEquals(expected, person.getBirthDate());
    }

    private static Stream<String> wrongGenderProvider() {
        return Stream.of("n", "s", "male", "female", "", null);
    }

    private static Stream<String> correctGenderProvider() {
        return Stream.of("f", "m", "M", "F");
    }

    private static Stream<String> correctDateProvider() {
        return Stream.of("2000-1-1", "1993-11-5", "1922-01-04", "2012-12-12");
    }

    private static Stream<String> wrongDateProvider() {
        return Stream.of("birthdate", null, "1822-2-1", "1999-15-12", "1999-12-32", "");
    }
}