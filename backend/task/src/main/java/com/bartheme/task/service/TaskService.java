package com.bartheme.task.service;

import com.bartheme.task.dto.TaskCreateDto;
import com.bartheme.task.model.Task;
import com.bartheme.task.dto.TaskDto;
import com.bartheme.task.model.TaskStatus;
import com.bartheme.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskDto getTaskById(Integer id) {
        Optional<Task> task =  taskRepository.findById(id);
        return task.map(TaskDto::new).orElse(null);
    }

    public TaskDto createTask(TaskCreateDto taskCreateDto) {
        Task task = new Task();
        task.setTitle(taskCreateDto.getTitle());
        task.setDescription(taskCreateDto.getDescription());
        task.setStatus(TaskStatus.TODO);
        task.setCategory(taskCreateDto.isUrgent(), taskCreateDto.isImportant());
        task.setCreatedAtDate(LocalDateTime.now());
        task.setDueDate(LocalDateTime.parse(taskCreateDto.getDueDate()));
        Task savedTask = taskRepository.save(task);
        return new TaskDto(savedTask);
    }
}
