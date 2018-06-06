package com.malevich.server.http.response.status.exception;

public class CanNotConnectException extends RuntimeException {

    private static final String MESSAGE = "Bad connection";

    public CanNotConnectException() {
        super(MESSAGE);
    }
}
