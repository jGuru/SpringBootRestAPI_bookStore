package com.bookstore.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Domain class to hold the error response
 *
 * @author Neeraj Sharma
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = -7203973328647136543L;

    private String title;
    private String details;

    /**
     * ErrorResponse constructor to construct error title and details
     *
     * @param title   The error title
     * @param details The error details
     */
    private ErrorResponse(String title, String details) {
        this.title = title;
        this.details = details;
    }

    /**
     * Builds the error response
     *
     * @param title   The error title
     * @param details The error details
     * @return ErrorResponse
     */
    public static ErrorResponse buildErrorResponse(String title, String details) {
        return new ErrorResponse(title, details);
    }
}
