package com.malevich.server.exception;

public class EntityAlreadyExistException extends RuntimeException {

    private static final String MESSAGE = "entity '%s' with %s already exist.";

    public EntityAlreadyExistException(String entity, String parameters) {
        super(String.format(MESSAGE, entity, parameters));
    }
}