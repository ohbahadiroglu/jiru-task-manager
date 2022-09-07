package com.phexum.jira.service;

import com.phexum.jira.FaturaState;
import com.phexum.jira.entity.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {
    Period create(Period period);

    List<Period> findAll();

    Optional<Period> findById(Long id);

    Period update(long periodId, String name, FaturaState state, long hourlyWageId);

    void periodCostUpdate(long periodId);

    void delete(Long id);
}
