package com.bookstore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PublisherNotFoundException extends RuntimeException {

    private String message;

    public PublisherNotFoundException(String message)
    {
        super(message);
    }
    public PublisherNotFoundException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
