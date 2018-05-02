package com.malevich.server.http.response.status.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class OkException extends RuntimeException {

    public static final String SAVED = "%s has been added to the database";
    public static final String UPDATED = "%s '%s' has been updated";
    public static final String REMOVED = "%s has been removed";

    public OkException(String action, String entity) {
        super(String.format(action, entity));
    }

    public OkException(String action, String entity, String parameters) {
        super(String.format(action, entity, parameters));
    }
}
