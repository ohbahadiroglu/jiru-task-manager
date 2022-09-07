package com.phexum.jira.service;


import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Task;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PeriodService periodService;


    public TaskServiceImpl(TaskRepository taskRepository, PeriodService periodService) {
        this.taskRepository = taskRepository;
        this.periodService = periodService;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task create(Task task) {
        try {
            return taskRepository.save(task);
        } finally {
            periodService.periodCostUpdate(task.getPeriod().getId());
        }
    }

    @Override
    public Task update(Task task, String summary, float totalWorkHours, String description) {
        task.setSummary(summary);
        task.setTotalHours(totalWorkHours);
        task.setDescription(description);
        periodService.periodCostUpdate(task.getPeriod().getId());
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        Optional<Task> op = taskRepository.findById(id);
        if (op.isEmpty()) {
            throw new NotFoundException(id);
        }
        taskRepository.deleteById(id);
        long periodId = op.get().getPeriod().getId();
        periodService.periodCostUpdate(periodId);

    }

    @Override
    public List<Task> getPeriodTasks(Period period) {

        return taskRepository.findByPeriod(period);
    }

}
