package com.bookstore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthorNotFoundException extends RuntimeException {

    private  String message;

    public AuthorNotFoundException(String message)
    {
        super(message);
    }
    public AuthorNotFoundException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
