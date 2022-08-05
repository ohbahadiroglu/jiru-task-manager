package com.phexum.jira.service;


import com.phexum.jira.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImp implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
