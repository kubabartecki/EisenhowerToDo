package com.bartheme.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskUpdateDto {
    private String title;
    private String description;
    @JsonProperty
    private boolean isUrgent;
    @JsonProperty
    private boolean isImportant;
    private String status;
    private String dueDate;
}
