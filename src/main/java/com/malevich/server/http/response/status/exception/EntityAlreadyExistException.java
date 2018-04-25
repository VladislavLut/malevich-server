package com.malevich.server.http.response.status.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityAlreadyExistException extends RuntimeException {

    private static final String MESSAGE = "entity '%s' with %s already exist.";

    public EntityAlreadyExistException(String entity, String parameters) {
        super(String.format(MESSAGE, entity, parameters));
    }
}