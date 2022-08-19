package com.phexum.jira.service;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {
    Period create(Period period);

    List<Period> findAll();

    Optional<Period> findById(Long id);

    Period update(long periodId, String name, String state, long hourlyWageId);

    void periodCostUpdate(long periodId);

    void delete(Long id);
}
