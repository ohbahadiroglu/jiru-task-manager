package com.phexum.jira.repository;

import com.phexum.jira.entity.AdditionalAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalAmountRepository extends JpaRepository<AdditionalAmount,Long> {
}
