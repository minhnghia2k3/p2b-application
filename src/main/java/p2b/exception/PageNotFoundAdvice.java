package p2b.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice //render into response body
public class PageNotFoundAdvice {
    @ExceptionHandler(PageNotFoundException.class) // response when page not found throw
    @ResponseStatus(HttpStatus.NOT_FOUND)
    RestErrorResponse pageNotFoundHandler(PageNotFoundException ex) {
        return new RestErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
    }
}
