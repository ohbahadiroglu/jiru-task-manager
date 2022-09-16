package com.phexum.jira.controller;

import com.phexum.jira.entity.Task;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.service.PeriodService;
import com.phexum.jira.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;
    private final PeriodService periodService;

    public TaskController(TaskService taskService, PeriodService periodService) {
        this.taskService = taskService;
        this.periodService = periodService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<Task> task = this.taskService.findById(id);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task.get());
    }

    @GetMapping("/period-tasks/{periodId}")
    public ResponseEntity getPeriodTask(@PathVariable("periodId") long periodId) {
        return ResponseEntity.ok(taskService.getPeriodTasks(periodService.findById(periodId).get()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            taskService.delete(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}

