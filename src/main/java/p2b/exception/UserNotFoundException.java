package p2b.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long userID) {
        super("not found user - " + userID);
    }
}
