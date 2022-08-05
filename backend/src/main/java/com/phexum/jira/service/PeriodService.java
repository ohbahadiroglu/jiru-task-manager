package com.phexum.jira.service;

import com.phexum.jira.entity.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {
    Period create(Period period);
    List<Period> findAll();
    Optional<Period> findById(Long id);
    void delete(Long id);
}
