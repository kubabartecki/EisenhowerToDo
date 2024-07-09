package com.bartheme.task.dto;

import lombok.Getter;

@Getter
public class TaskUpdateDto {
    private String title;
    private String description;
    private String status;
    private String category;
    private String dueDate;
}
