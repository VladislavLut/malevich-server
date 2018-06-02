package com.malevich.server.exception;

public class SessionAlreadyOpened extends RuntimeException {
    public SessionAlreadyOpened() {
        super();
    }

    public SessionAlreadyOpened(String message) {
        super(message);
    }
}
