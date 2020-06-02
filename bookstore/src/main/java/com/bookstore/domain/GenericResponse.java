package com.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 * Domain class for generic response
 *
 * @author Neeraj Sharma
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse extends ErrorResponse implements Serializable {
    private static final long serialVersionUID = -7203973328647138754L;

    private boolean success;

    /**
     * Is success
     *
     * @return true if response is success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success
     *
     * @param success The flag success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}