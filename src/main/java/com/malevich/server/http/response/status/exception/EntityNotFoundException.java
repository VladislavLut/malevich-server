package com.malevich.server.http.response.status.exception;

public class EntityNotFoundException extends RuntimeException {

    private final static String MESSAGE = "could not found entity '%s' with %s.";

    public EntityNotFoundException(String entity, String parameters) {
        super(String.format(MESSAGE, entity, parameters));
    }
}
