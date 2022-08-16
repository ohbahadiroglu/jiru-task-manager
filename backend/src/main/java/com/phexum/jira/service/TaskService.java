package com.phexum.jira.service;

import com.phexum.jira.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Optional<Task> findByCode(String code);

    Task create(Task task);

    void delete(Long id);


}
