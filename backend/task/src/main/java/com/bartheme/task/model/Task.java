package com.bartheme.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_id_sequence",
            sequenceName = "task_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_sequence"
    )
    private Integer id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskEisenhowerCategory category;
    private LocalDateTime createdAtDate;
    private LocalDateTime dueDate;

    public void setCategory(boolean isUrgent, boolean isImportant) {
        this.category = TaskEisenhowerCategory.fromValue(isUrgent, isImportant);
    }
}
