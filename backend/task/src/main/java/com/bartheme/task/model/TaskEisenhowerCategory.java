package com.bartheme.task.model;

public enum TaskEisenhowerCategory {
    DO(10),
    SCHEDULE(20),
    DELEGATE(30),
    ELIMINATE(40);

    private int value;
    private TaskEisenhowerCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TaskEisenhowerCategory fromValue(boolean isUrgent, boolean isImportant) {
        if(isUrgent) {
            if (isImportant) {
                return DO;
            }
            return DELEGATE;
        }
        if (isImportant) {
            return SCHEDULE;
        }
        return ELIMINATE;
    }
}
