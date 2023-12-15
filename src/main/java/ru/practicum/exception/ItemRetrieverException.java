package ru.practicum.exception;


public class ItemRetrieverException extends LaterException {
    public ItemRetrieverException(String message) {
        super(message);
    }

    public ItemRetrieverException(String message, Throwable cause) {
        super(message, cause);
    }
}