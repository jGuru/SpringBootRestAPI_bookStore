package com.bookstore.exception;

import com.bookstore.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    private HttpHeaders headers;

    public ErrorHandler() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }


    /**
     * Generic error exception handler
     *
     * @param t The throwable
     * @return The response entity of the error response
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> genericError(Throwable t) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return buildErrorResponse(status, status.getReasonPhrase(), t.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookValidationException(BookNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return buildErrorResponse(status, status.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> authorValidationException(AuthorNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return buildErrorResponse(status, status.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler(PublisherNotFoundException.class)
    public ResponseEntity<ErrorResponse> publisherValidationException(PublisherNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return buildErrorResponse(status, status.getReasonPhrase(), e.getMessage());
    }


    /**
     * Builds the error response for a corresponding exception
     *
     * @param status  The http status
     * @param title   The title
     * @param details The details
     * @return ResponseEntity
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String title, String details) {
        return new ResponseEntity<>(ErrorResponse.buildErrorResponse(title, details), headers, status);
    }
}
