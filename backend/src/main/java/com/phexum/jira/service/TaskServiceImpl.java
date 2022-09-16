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
    public List<Task> create(List<Task> tasks) {
        try {
            return taskRepository.saveAll(tasks);
        } finally {
            periodService.periodCostUpdate(tasks.get(0).getPeriod().getId());
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
    public void delete(List<Long> idList) {
        List<Task> op = taskRepository.findAllById(idList);
        if (op.isEmpty()) {
            throw new NotFoundException("idList: bu idlere ait hiçbir task bulunamadı");
        }
        taskRepository.deleteAllById(idList);
        long periodId = op.get(0).getPeriod().getId();
        periodService.periodCostUpdate(periodId);

    }

    @Override
    public List<Task> getPeriodTasks(Period period) {

        return taskRepository.findByPeriod(period);
    }

}
