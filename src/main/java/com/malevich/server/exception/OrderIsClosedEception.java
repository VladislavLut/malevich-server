package com.malevich.server.exception;

public class OrderIsClosedEception extends RuntimeException {

    public OrderIsClosedEception() {
        super();
    }

    public OrderIsClosedEception(String message) {
        super(message);
    }

}
