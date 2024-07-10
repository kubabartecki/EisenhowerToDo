package com.bartheme.task.dto;


import com.bartheme.task.model.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Integer id;
    private String title;
    private String description;
    private String category;
    private String status;
    private String dueDate;

    public TaskDto(Task task) {
        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        category = task.getCategory().name();
        status = task.getStatus().name();
        dueDate = task.getDueDate().toString();
    }
}
