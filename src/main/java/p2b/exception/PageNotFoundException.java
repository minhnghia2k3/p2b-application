package p2b.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String msg) {
        super(msg);
    }
}
