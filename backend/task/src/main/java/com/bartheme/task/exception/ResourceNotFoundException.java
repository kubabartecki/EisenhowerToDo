package com.bartheme.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;
    public static final String MESSAGE_PREFIX = "Task not found for id: ";

    public ResourceNotFoundException(Integer id){
        super(MESSAGE_PREFIX + id);
    }
}
