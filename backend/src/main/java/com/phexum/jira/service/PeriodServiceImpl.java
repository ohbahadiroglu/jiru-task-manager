package com.phexum.jira.service;

import com.phexum.jira.FaturaState;
import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Task;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.AdditionalAmountRepository;
import com.phexum.jira.repository.HourlyWageRepository;
import com.phexum.jira.repository.PeriodRepository;
import com.phexum.jira.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    private final PeriodRepository periodRepository;
    private final TaskRepository taskRepository;
    private final AdditionalAmountRepository additionalAmountRepository;
    private final HourlyWageRepository hourlyWageRepository;

    public PeriodServiceImpl(PeriodRepository periodRepository, TaskRepository taskRepository, AdditionalAmountRepository additionalAmountRepository, HourlyWageRepository hourlyWageRepository) {
        this.periodRepository = periodRepository;
        this.taskRepository = taskRepository;
        this.additionalAmountRepository = additionalAmountRepository;
        this.hourlyWageRepository = hourlyWageRepository;
    }

    public Period create(Period period) {
        return periodRepository.save(period);
    }

    public List<Period> findAll() {
        List<Period> period = periodRepository.findAll();
        return period;
    }

    public Optional<Period> findById(Long id) {
        return periodRepository.findById(id);
    }

    @Override
    public Period update(long periodId, String name, FaturaState state, long hourlyWageId) {
        Period period = periodRepository.findById(periodId).get();
        period.setName(name);
        period.setState(state);
        period.setHourlyWage(hourlyWageRepository.findById(hourlyWageId).get());
        periodRepository.save(period);
        periodCostUpdate(periodId);
        return period;
    }

    public void periodCostUpdate(long periodId) {
        double total = 0;
        Period period = periodRepository.findById(periodId).get();
        HourlyWage hourlyWage = period.getHourlyWage();
        List<Task> tasks = taskRepository.findByPeriod(period);
        List<AdditionalAmount> additionalAmounts = additionalAmountRepository.findByPeriod(period);

        for (AdditionalAmount additionalAmount : additionalAmounts) {
            total += additionalAmount.getAmount();
        }

        for (Task task : tasks) {
            total += (task.getTotalHours() * hourlyWage.getAmount());
        }
        period.setCost(total);
        periodRepository.save(period);
    }

    public void delete(Long id) {
        Optional<Period> op = periodRepository.findById(id);
        if (op.isEmpty()) {
            throw new NotFoundException(id);
        }
        List<AdditionalAmount> additionalAmounts = additionalAmountRepository.findByPeriod(op.get());
        for (AdditionalAmount a : additionalAmounts) {
            additionalAmountRepository.delete(a);
        }
        periodRepository.deleteById(id);
    }
}
