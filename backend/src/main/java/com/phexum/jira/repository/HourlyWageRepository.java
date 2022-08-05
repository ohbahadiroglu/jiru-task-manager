package com.phexum.jira.repository;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.entity.HourlyWage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyWageRepository extends JpaRepository<HourlyWage,Long> {
}
