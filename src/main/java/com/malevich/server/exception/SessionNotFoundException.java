package com.malevich.server.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException() {
        super();
    }

    public SessionNotFoundException(String message) {
        super(message);
    }
}
