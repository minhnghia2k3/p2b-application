package p2b.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super("not found file: " + message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super("not found file: " + message, cause);
    }
}
