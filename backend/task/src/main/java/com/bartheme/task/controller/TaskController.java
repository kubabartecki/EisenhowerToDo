package com.bartheme.task.controller;

import com.bartheme.task.dto.TaskCreateDto;
import com.bartheme.task.dto.TaskDto;
import com.bartheme.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) {
        TaskDto taskDto = taskService.getTaskById(id);
        if (taskDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.ok(taskService.createTask(taskCreateDto));
    }
}
