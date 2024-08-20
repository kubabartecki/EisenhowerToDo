package com.bartheme.task.repository;

import com.bartheme.task.model.Task;
import com.bartheme.task.model.TaskEisenhowerCategory;
import com.bartheme.task.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void PostgreSQLContainer_ConnectionTest_IsRunning() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

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
