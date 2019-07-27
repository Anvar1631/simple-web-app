package com.example.simpleapp.utils;

public enum Status {
    ENABLED(1),
    DISABLED(0);

    int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
