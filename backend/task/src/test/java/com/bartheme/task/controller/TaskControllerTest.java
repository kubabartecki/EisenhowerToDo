package com.bartheme.task.controller;

import com.bartheme.task.dto.TaskCreateDto;
import com.bartheme.task.dto.TaskDto;
import com.bartheme.task.dto.TaskUpdateDto;
import com.bartheme.task.model.TaskEisenhowerCategory;
import com.bartheme.task.model.TaskStatus;
import com.bartheme.task.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void TaskController_CreateTask_ReturnCreated() throws Exception {
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

        given(taskService.createTask(any(TaskCreateDto.class))).willReturn(expectedTaskDto);

        ResultActions response = mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskCreateDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(expectedTaskDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(expectedTaskDto.getStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(expectedTaskDto.getCategory())));
    }

    @Test
    void TaskController_UpdateTask_ReturnCreated() throws Exception {
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

        TaskDto updatedTaskDto = TaskDto.builder()
                .id(taskId)
                .title(newTaskTitle)
                .description(taskDescription)
                .status(newTaskEisenhowerCategory.name())
                .category(newTaskStatus.name())
                .dueDate(dateTime.toString())
                .build();

        given(taskService.updateTask(any(), any(TaskUpdateDto.class))).willReturn(updatedTaskDto);

        ResultActions response = mockMvc.perform(put("/api/v1/tasks/" + taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskUpdateDto)));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(updatedTaskDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(updatedTaskDto.getStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is(updatedTaskDto.getCategory())));
    }
}
