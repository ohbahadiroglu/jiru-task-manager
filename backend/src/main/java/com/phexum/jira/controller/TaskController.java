package com.phexum.jira.controller;

import com.phexum.jira.entity.Task;
import com.phexum.jira.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> allTasks = this.taskService.findAll();
        return new ResponseEntity<List<Task>>(allTasks, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Task>> findById(@PathVariable("id") Long id) {
        Optional<Task> task = this.taskService.findById(id);
        return new ResponseEntity<Optional<Task>>(task, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task taskNew = this.taskService.create(task);
        return new ResponseEntity<Task>(taskNew, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }
}
