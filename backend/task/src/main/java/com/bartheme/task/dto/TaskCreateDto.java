package com.bartheme.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskCreateDto {
    private String title;
    private String description;
    @JsonProperty
    private boolean isUrgent;
    @JsonProperty
    private boolean isImportant;
    private String dueDate;
}
