package ru.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbidenException extends LaterException {
    public ForbidenException(String message) {
        super(message);
    }

    public ForbidenException(String message, Throwable cause) {
        super(message, cause);
    }
}