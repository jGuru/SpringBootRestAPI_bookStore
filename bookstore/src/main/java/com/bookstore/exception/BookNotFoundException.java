package com.bookstore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookNotFoundException extends RuntimeException {

    private String message;

    public BookNotFoundException(String message)
    {
        super(message);
    }
    public BookNotFoundException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
