package com.bartheme.task.model;

public enum TaskStatus {
    TODO(10),
    IN_PROGRESS(20),
    DONE(30);

    private int value;
    private TaskStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
