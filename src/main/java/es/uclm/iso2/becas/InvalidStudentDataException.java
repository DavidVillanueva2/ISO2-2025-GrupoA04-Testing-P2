package es.uclm.iso2.becas;

/**
 * Thrown when a student's profile data is missing or outside its valid range.
 */
public class InvalidStudentDataException extends RuntimeException {

    public InvalidStudentDataException(String message) {
        super(message);
    }
}
