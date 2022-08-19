package com.phexum.jira.repository;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {
    Optional<List<Period>> findByHourlyWage(HourlyWage hourlyWage);
}
