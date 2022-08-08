package com.smart.dxb.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
