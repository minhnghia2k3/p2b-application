package p2b.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(long id) {
        super("not found book by id: " + id);
    }
}