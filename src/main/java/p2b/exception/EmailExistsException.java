package p2b.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String email) {
        super("email already exists " + email);
    }
}
