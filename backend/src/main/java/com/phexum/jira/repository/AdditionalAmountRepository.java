package com.phexum.jira.repository;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalAmountRepository extends JpaRepository<AdditionalAmount, Long> {
    List<AdditionalAmount> findByPeriod(Period period);
}
