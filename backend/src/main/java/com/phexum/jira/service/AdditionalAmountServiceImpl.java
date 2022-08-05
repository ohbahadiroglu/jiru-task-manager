package com.phexum.jira.service;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.repository.AdditionalAmountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalAmountServiceImpl implements AdditionalAmountService{
    private final AdditionalAmountRepository additionalAmountRepository;

    public AdditionalAmountServiceImpl(AdditionalAmountRepository additionalAmountRepository) {
        this.additionalAmountRepository = additionalAmountRepository;
    }

    @Override
    public AdditionalAmount create(AdditionalAmount additionalAmount) {
        return null;
    }

    @Override
    public List<AdditionalAmount> findAll() {
        return null;
    }

    @Override
    public Optional<AdditionalAmount> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
