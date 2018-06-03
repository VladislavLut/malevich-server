package com.malevich.server.exception;

public class SessionIsAlreadyOpened extends RuntimeException {
    public SessionIsAlreadyOpened() {
        super();
    }

    public SessionIsAlreadyOpened(String message) {
        super(message);
    }
}
