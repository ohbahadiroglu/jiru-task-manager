package com.phexum.jira.service;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Site;

import java.util.List;
import java.util.Optional;

public interface HourlyWageService {
    HourlyWage create(HourlyWage hourlyWage);

    List<HourlyWage> findAll();

    Optional<HourlyWage> findById(Long id);

    void delete(Long id);
}
