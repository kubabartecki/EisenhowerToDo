package com.bartheme.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableContentException extends Exception {

    private static final long serialVersionUID = 2L;

    public UnprocessableContentException(String message) {
        super(message);
    }
}