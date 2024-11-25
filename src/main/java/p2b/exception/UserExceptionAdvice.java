package p2b.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    RestErrorResponse handleNotFoundUser(UserNotFoundException ex) {
        return new RestErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    RestErrorResponse handleEmailExistsUser(EmailExistsException ex) {
        return new RestErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
