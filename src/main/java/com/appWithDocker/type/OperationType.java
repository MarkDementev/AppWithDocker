package com.appWithDocker.type;

public enum OperationType {
    DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW");

    private final String title;

    OperationType(String title) {
        this.title = title;
    }
}
