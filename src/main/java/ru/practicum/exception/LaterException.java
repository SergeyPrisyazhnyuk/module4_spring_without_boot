package ru.practicum.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LaterException extends RuntimeException {
    public LaterException(String message) {
        super(message);
    }

    public LaterException(String message, Throwable cause) {
        super(message, cause);
    }
}