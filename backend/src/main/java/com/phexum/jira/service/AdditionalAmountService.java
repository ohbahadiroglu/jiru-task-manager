package com.phexum.jira.service;


import com.phexum.jira.entity.AdditionalAmount;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdditionalAmountService {
    AdditionalAmount create(AdditionalAmount additionalAmount);

    List<AdditionalAmount> findAll();

    Optional<AdditionalAmount> findById(Long id);

    AdditionalAmount update(long id, double amount, String name);

    void delete(Long id);
}
