package com.bartheme.task.service;

import com.bartheme.task.dto.TaskCreateDto;
import com.bartheme.task.dto.TaskUpdateDto;
import com.bartheme.task.exception.ResourceNotFoundException;
import com.bartheme.task.exception.UnprocessableContentException;
import com.bartheme.task.model.Task;
import com.bartheme.task.dto.TaskDto;
import com.bartheme.task.model.TaskEisenhowerCategory;
import com.bartheme.task.model.TaskStatus;
import com.bartheme.task.repository.TaskRepository;
import com.bartheme.task.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskDto getTaskById(Integer id) throws ResourceNotFoundException {
        Task task =  taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for id: " + id));
        return new TaskDto(task);
    }

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskDto::new).toList();
    }

    public List<TaskDto> filterTasks(List<String> statuses, List<String> categories, String title, Sort sort) {
        TaskSpecification spec = new TaskSpecification(statuses, categories, title);
        List<Task> tasks = taskRepository.findAll(spec, sort);
        return tasks.stream().map(TaskDto::new).toList();
    }

    public TaskDto createTask(TaskCreateDto taskCreateDto) {
        Task task = createTaskFromTaskCreateDto(taskCreateDto);
        Task savedTask = taskRepository.save(task);
        return new TaskDto(savedTask);
    }

    public TaskDto updateTask(Integer id, TaskUpdateDto taskUpdateDto)
            throws ResourceNotFoundException, UnprocessableContentException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for id: " + id));
        task.setTitle(taskUpdateDto.getTitle());
        task.setDescription(taskUpdateDto.getDescription());
        try {
            task.setStatus(TaskStatus.valueOf(taskUpdateDto.getStatus()));
        } catch (IllegalArgumentException e) {
            throw new UnprocessableContentException("Not valid status enum value");
        }
        try {
            task.setCategory(TaskEisenhowerCategory.valueOf(taskUpdateDto.getCategory()));
        } catch (IllegalArgumentException e) {
            throw new UnprocessableContentException("Not valid category enum value");
        }
        task.setDueDate(LocalDateTime.parse(taskUpdateDto.getDueDate()));
        return new TaskDto(taskRepository.save(task));
    }


    public String deleteTask(Integer id) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for id: " + id));
        taskRepository.delete(task);
        return "Task deleted";
    }

    private Task createTaskFromTaskCreateDto(TaskCreateDto taskCreateDto) {
        Task task = new Task();
        task.setTitle(taskCreateDto.getTitle());
        task.setDescription(taskCreateDto.getDescription());
        task.setStatus(TaskStatus.TODO);
        task.setCategory(taskCreateDto.isUrgent(), taskCreateDto.isImportant());
        task.setCreatedAtDate(LocalDateTime.now());
        task.setDueDate(LocalDateTime.parse(taskCreateDto.getDueDate()));
        return task;
    }
}
