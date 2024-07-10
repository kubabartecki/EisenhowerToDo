package com.bartheme.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDto {
    private String title;
    private String description;
    private String status;
    private String category;
    private String dueDate;
}
