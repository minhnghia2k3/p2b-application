package p2b.exception;

import java.time.LocalDateTime;

public record RestErrorResponse(int status, String message, LocalDateTime timestamp ) {
}
