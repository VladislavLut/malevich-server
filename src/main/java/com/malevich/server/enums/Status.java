package com.malevich.server.enums;

public enum Status {
    WAITING(0),
    PROCESSING(1),
    DONE(2),
    WAITING_FOR_PAYMENT(3),
    CLOSED(4);

    private int index;

    Status(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
