package com.phexum.jira.service;


import com.phexum.jira.entity.Task;
import com.phexum.jira.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.of(taskRepository.getById(id));
    }

    @Override
    public Task create(Task task) {
        if (task.getName() != "" && task.getType() != "") {
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
