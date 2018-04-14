package com.malevich.server.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(Object entity, String message) {
        super("entity '" + entity.getClass() + "' with " + message + " already exist.");
    }
}