package com.phexum.jira.repository;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HourlyWageRepository extends JpaRepository<HourlyWage,Long> {
    Optional<HourlyWage> findByName(String name);
}
