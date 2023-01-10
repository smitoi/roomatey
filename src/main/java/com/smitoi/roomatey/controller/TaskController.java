package com.smitoi.roomatey.controller;

import com.smitoi.roomatey.dto.requests.task.CreateTaskRequest;
import com.smitoi.roomatey.dto.requests.task.UpdateTaskRequest;
import com.smitoi.roomatey.dto.responses.TaskDto;
import com.smitoi.roomatey.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Tasks related endpoints.")
public class TaskController extends BaseController {

    @Autowired
    private final TaskService service;

    @GetMapping("/{groupId}")
    @Operation(summary = "Index", description = "View all tasks from a certain group.")
    public ResponseEntity<List<TaskDto>> index(@PathVariable Long groupId, @RequestParam String search) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getTasksForGroup(groupId, search, getLoggedInUser()));
    }

    @PostMapping("/{groupId}")
    @Operation(summary = "Store", description = "Create a new task inside a group.")
    public ResponseEntity<TaskDto> store(@PathVariable Long groupId, @Valid @RequestBody CreateTaskRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addTask(request, groupId, getLoggedInUser()));
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Update", description = "Edit an existing task.")
    public ResponseEntity<TaskDto> update(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.editTask(request, taskId, getLoggedInUser()));
    }
}
