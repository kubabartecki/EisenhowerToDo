package com.bartheme.task.service;

import com.bartheme.task.dto.TaskCreateDto;
import com.bartheme.task.dto.TaskDto;
import com.bartheme.task.dto.TaskUpdateDto;
import com.bartheme.task.exception.ResourceNotFoundException;
import com.bartheme.task.exception.UnprocessableContentException;
import com.bartheme.task.model.Task;
import com.bartheme.task.model.TaskEisenhowerCategory;
import com.bartheme.task.model.TaskStatus;
import com.bartheme.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void TaskService_CreateTask_ReturnsTaskDto() {
        int taskId = 1;
        String taskTitle = "Task Title";
        String taskDescription = "Task Description";
        TaskStatus taskStatus = TaskStatus.TODO;
        TaskEisenhowerCategory taskEisenhowerCategory = TaskEisenhowerCategory.DO;
        LocalDateTime dateTime = LocalDateTime.now();

        TaskCreateDto taskCreateDto = TaskCreateDto.builder()
                .title(taskTitle)
                .description(taskDescription)
                .isUrgent(true)
                .isImportant(true)
                .dueDate(dateTime.toString())
                .build();

        TaskDto expectedTaskDto = TaskDto.builder()
                .id(taskId)
                .title(taskTitle)
                .description(taskDescription)
                .category(taskEisenhowerCategory.name())
                .status(taskStatus.name())
                .dueDate(dateTime.toString())
                .build();

        Task task = Task.builder()
                .id(taskId)
                .title(taskTitle)
                .description(taskDescription)
                .status(taskStatus)
                .category(taskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        given(taskRepository.save(any(Task.class))).willReturn(task);

        TaskDto savedTaskDto = taskService.createTask(taskCreateDto);

        assertThat(savedTaskDto).isNotNull().isEqualTo(expectedTaskDto);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void TaskService_UpdateTask_ReturnsUpdatedTaskDto() throws ResourceNotFoundException, UnprocessableContentException {
        int taskId = 1;
        String taskTitle = "Task Title";
        String taskDescription = "Task Description";
        TaskStatus taskStatus = TaskStatus.TODO;
        TaskEisenhowerCategory taskEisenhowerCategory = TaskEisenhowerCategory.DO;
        LocalDateTime dateTime = LocalDateTime.now();

        String newTaskTitle = "New Task Title";
        TaskStatus newTaskStatus = TaskStatus.IN_PROGRESS;
        TaskEisenhowerCategory newTaskEisenhowerCategory = TaskEisenhowerCategory.DELEGATE;

        TaskUpdateDto taskUpdateDto = TaskUpdateDto.builder()
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskStatus.name())
                .category(newTaskEisenhowerCategory.name())
                .dueDate(dateTime.toString())
                .build();

        TaskDto expectedTaskDto = TaskDto.builder()
                .id(taskId)
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskStatus.name())
                .category(newTaskEisenhowerCategory.name())
                .dueDate(dateTime.toString())
                .build();

        Task originalTask = Task.builder()
                .id(taskId)
                .title(taskTitle)
                .description(taskDescription)
                .status(taskStatus)
                .category(taskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        Task updatedTask = Task.builder()
                .id(taskId)
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskStatus)
                .category(newTaskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        given(taskRepository.findById(taskId)).willReturn(Optional.ofNullable(originalTask));
        given(taskRepository.save(any(Task.class))).willReturn(updatedTask);

        TaskDto updatedTaskDto = taskService.updateTask(taskId, taskUpdateDto);

        assertThat(updatedTaskDto).isNotNull().isEqualTo(expectedTaskDto);
        verify(taskRepository, times(1)).findById(any());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void TaskService_UpdateTask_ThrowsResourceNotFoundException() {
        int taskId = 1;
        String taskDescription = "Task Description";
        LocalDateTime dateTime = LocalDateTime.now();

        String newTaskTitle = "New Task Title";
        TaskStatus newTaskStatus = TaskStatus.IN_PROGRESS;
        TaskEisenhowerCategory newTaskEisenhowerCategory = TaskEisenhowerCategory.DELEGATE;

        TaskUpdateDto taskUpdateDto = TaskUpdateDto.builder()
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskStatus.name())
                .category(newTaskEisenhowerCategory.name())
                .dueDate(dateTime.toString())
                .build();

        given(taskRepository.findById(taskId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.updateTask(taskId, taskUpdateDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Task not found for id: " + taskId);

        verify(taskRepository, never()).save(any());
    }

    @Test
    void TaskService_UpdateTask_ThrowsUnprocessableContentExceptionOnTaskStatus() {
        int taskId = 1;
        String taskTitle = "Task Title";
        String taskDescription = "Task Description";
        TaskStatus taskStatus = TaskStatus.TODO;
        TaskEisenhowerCategory taskEisenhowerCategory = TaskEisenhowerCategory.DO;
        LocalDateTime dateTime = LocalDateTime.now();

        String newTaskTitle = "New Task Title";
        String newTaskStatusName = "In progress";
        TaskEisenhowerCategory newTaskEisenhowerCategory = TaskEisenhowerCategory.DELEGATE;

        TaskUpdateDto taskUpdateDto = TaskUpdateDto.builder()
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskStatusName)
                .category(newTaskEisenhowerCategory.name())
                .dueDate(dateTime.toString())
                .build();

        Task originalTask = Task.builder()
                .id(taskId)
                .title(taskTitle)
                .description(taskDescription)
                .status(taskStatus)
                .category(taskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        given(taskRepository.findById(taskId)).willReturn(Optional.ofNullable(originalTask));

        assertThatThrownBy(() -> taskService.updateTask(taskId, taskUpdateDto))
                .isInstanceOf(UnprocessableContentException.class)
                .hasMessageContaining("Not valid status enum value: " + newTaskStatusName);

        verify(taskRepository, times(1)).findById(any());
        verify(taskRepository, never()).save(any());
    }

    @Test
    void TaskService_UpdateTask_ThrowsUnprocessableContentExceptionOnTaskCategory() {
        int taskId = 1;
        String taskTitle = "Task Title";
        String taskDescription = "Task Description";
        TaskStatus taskStatus = TaskStatus.TODO;
        TaskEisenhowerCategory taskEisenhowerCategory = TaskEisenhowerCategory.DO;
        LocalDateTime dateTime = LocalDateTime.now();

        String newTaskTitle = "New Task Title";
        TaskStatus newTaskStatus = TaskStatus.IN_PROGRESS;
        String newTaskEisenhowerCategoryName = "Delegate";

        TaskUpdateDto taskUpdateDto = TaskUpdateDto.builder()
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskStatus.name())
                .category(newTaskEisenhowerCategoryName)
                .dueDate(dateTime.toString())
                .build();

        Task originalTask = Task.builder()
                .id(taskId)
                .title(taskTitle)
                .description(taskDescription)
                .status(taskStatus)
                .category(taskEisenhowerCategory)
                .createdAtDate(dateTime)
                .dueDate(dateTime)
                .build();

        given(taskRepository.findById(taskId)).willReturn(Optional.ofNullable(originalTask));

        assertThatThrownBy(() -> taskService.updateTask(taskId, taskUpdateDto))
                .isInstanceOf(UnprocessableContentException.class)
                .hasMessageContaining("Not valid category enum value: " + newTaskEisenhowerCategoryName);

        verify(taskRepository, times(1)).findById(any());
        verify(taskRepository, never()).save(any());
    }
}
