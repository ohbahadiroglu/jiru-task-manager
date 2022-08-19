package com.phexum.jira.service;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Period;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.HourlyWageRepository;
import com.phexum.jira.repository.PeriodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HourlyWageServiceImpl implements HourlyWageService {

    private final HourlyWageRepository hourlyWageRepository;
    private final PeriodRepository periodRepository;

    private final PeriodService periodService;

    public HourlyWageServiceImpl(HourlyWageRepository hourlyWageRepository, PeriodRepository periodRepository, PeriodService periodService) {
        this.hourlyWageRepository = hourlyWageRepository;
        this.periodRepository = periodRepository;
        this.periodService = periodService;
    }

    @Override
    public HourlyWage create(HourlyWage hourlyWage) throws Exception {
        Optional<HourlyWage> op = hourlyWageRepository.findByName(hourlyWage.getName());
        if (op.isPresent()) {
            throw new Exception();
        }
        hourlyWageRepository.save(hourlyWage);
        return hourlyWage;
    }

    @Override
    public List<HourlyWage> findAll() {
        List<HourlyWage> hourlyWages = hourlyWageRepository.findAll();
        return hourlyWages;
    }

    @Override
    public Optional<HourlyWage> findById(Long id) {
        return hourlyWageRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Optional<HourlyWage> op = hourlyWageRepository.findById(id);
        if (op.isEmpty()) {
            throw new NotFoundException(id);
        }
        hourlyWageRepository.deleteById(id);
        Optional<List<Period>> periods = periodRepository.findByHourlyWage(op.get());
        if (periods.isPresent()) {
            for (Period period : periods.get()) {
                periodService.periodCostUpdate(period.getId());
            }
        }
    }

    @Override
    public HourlyWage update(long hourlyWageId, double amount, String name) {
        HourlyWage hourlyWage = hourlyWageRepository.findById(hourlyWageId).get();
        Optional<List<Period>> periods = periodRepository.findByHourlyWage(hourlyWage);
        hourlyWage.setAmount(amount);
        hourlyWage.setName(name);
        hourlyWageRepository.save(hourlyWage);
        if (periods.isPresent()) {
            for (Period period : periods.get()) {
                periodService.periodCostUpdate(period.getId());
            }
        }
        return hourlyWage;
    }
}
