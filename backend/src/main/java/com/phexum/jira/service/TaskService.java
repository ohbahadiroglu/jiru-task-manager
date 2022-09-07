package com.phexum.jira.service;

import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task create(Task task);

    Task update(Task task, String summary, float totalWorkHours, String description);

    void delete(Long id);

    List<Task> getPeriodTasks(Period period);
}
