package com.phexum.jira.service;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.HourlyWageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HourlyWageServiceImpl implements HourlyWageService{

    private final HourlyWageRepository hourlyWageRepository;

    public HourlyWageServiceImpl(HourlyWageRepository hourlyWageRepository) {
        this.hourlyWageRepository = hourlyWageRepository;
    }

    @Override
    public HourlyWage create(HourlyWage hourlyWage) {
        return hourlyWageRepository.save(hourlyWage);
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
    }
}
