package com.malevich.server.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Object entity, int id) {
        super("could not found entity '" + entity.getClass() + "' with id '" + id + "'.");
    }

    public EntityNotFoundException(Object entity, String login) {
        super("could not found entity '" + entity.getClass() + "' with login '" + login + "'.");
    }
}
