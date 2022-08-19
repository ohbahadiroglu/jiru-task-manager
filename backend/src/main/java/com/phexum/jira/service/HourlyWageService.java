package com.phexum.jira.service;

import com.phexum.jira.entity.HourlyWage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HourlyWageService {
    HourlyWage create(HourlyWage hourlyWage) throws Exception;

    List<HourlyWage> findAll();

    Optional<HourlyWage> findById(Long id);

    void delete(Long id);

    HourlyWage update(long hourlyWageId, double amount, String name);
}
