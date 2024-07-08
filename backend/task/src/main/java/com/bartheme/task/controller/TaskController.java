package com.bartheme.task.controller;

import com.bartheme.task.dto.TaskCreateDto;
import com.bartheme.task.dto.TaskDto;
import com.bartheme.task.dto.TaskUpdateDto;
import com.bartheme.task.exception.ResourceNotFoundException;
import com.bartheme.task.exception.UnprocessableContentException;
import com.bartheme.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) throws ResourceNotFoundException {
        TaskDto taskDto = taskService.getTaskById(id);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> taskList = taskService.getAllTasks();
        return ResponseEntity.ok(taskList);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(taskService.createTask(taskCreateDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Integer id, @RequestBody TaskUpdateDto taskUpdateDto)
            throws ResourceNotFoundException, UnprocessableContentException {
        return ResponseEntity.ok(taskService.updateTask(id, taskUpdateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
