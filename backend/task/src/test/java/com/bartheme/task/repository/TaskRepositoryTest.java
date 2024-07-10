package com.bartheme.task.repository;

import com.bartheme.task.model.Task;
import com.bartheme.task.model.TaskEisenhowerCategory;
import com.bartheme.task.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void TaskRepository_SaveTask_ReturnsSameSavedTask() {
        // arrange
        String taskTitle = "Task Title";
        String taskDescription = "Task Description";
        TaskStatus taskStatus = TaskStatus.TODO;
        TaskEisenhowerCategory taskEisenhowerCategory = TaskEisenhowerCategory.DO;
        LocalDateTime dateTime = LocalDateTime.now();

        Task task = Task.builder()
                .title(taskTitle)
                .description(taskDescription)
                .status(taskStatus)
                .category(taskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        // act
        Task savedTasks = taskRepository.save(task);

        // assert
        assertThat(savedTasks.getId()).isPositive();
        assertThat(savedTasks.getTitle()).isEqualTo(taskTitle);
        assertThat(savedTasks.getDescription()).isEqualTo(taskDescription);
        assertThat(savedTasks.getStatus()).isEqualTo(taskStatus);
        assertThat(savedTasks.getCategory()).isEqualTo(taskEisenhowerCategory);
        assertThat(savedTasks.getCreatedAtDate()).isEqualTo(dateTime);
        assertThat(savedTasks.getDueDate()).isEqualTo(dateTime);
    }

    @Test
    void TaskRepository_UpdateSomeTaskFields_ReturnsUpdatedTask() {
        // arrange
        String taskTitle = "Task Title";
        String taskDescription = "Task Description";
        TaskStatus taskStatus = TaskStatus.TODO;
        TaskEisenhowerCategory taskEisenhowerCategory = TaskEisenhowerCategory.DO;
        LocalDateTime dateTime = LocalDateTime.now();

        Task task = Task.builder()
                .title(taskTitle)
                .description(taskDescription)
                .status(taskStatus)
                .category(taskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        Task savedTask = taskRepository.save(task);
        String newTaskTitle = "New Task Title";
        TaskStatus newTaskStatus = TaskStatus.IN_PROGRESS;
        LocalDateTime newDateTime = LocalDateTime.now().plusDays(1);

        // act
        savedTask.setTitle(newTaskTitle);
        savedTask.setStatus(newTaskStatus);
        savedTask.setDueDate(newDateTime);
        Task updatedTask = taskRepository.save(savedTask);

        // assert
        assertThat(updatedTask.getId()).isEqualTo(savedTask.getId());
        assertThat(updatedTask.getTitle()).isEqualTo(newTaskTitle);
        assertThat(updatedTask.getDescription()).isEqualTo(savedTask.getDescription());
        assertThat(updatedTask.getStatus()).isEqualTo(newTaskStatus);
        assertThat(updatedTask.getCategory()).isEqualTo(savedTask.getCategory());
        assertThat(updatedTask.getDueDate()).isEqualTo(newDateTime);
        assertThat(updatedTask.getCreatedAtDate()).isEqualTo(savedTask.getCreatedAtDate());
    }
}
