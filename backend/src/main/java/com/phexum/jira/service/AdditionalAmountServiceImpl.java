package com.phexum.jira.service;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.AdditionalAmountRepository;
import com.phexum.jira.repository.SiteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalAmountServiceImpl implements AdditionalAmountService{
    private final AdditionalAmountRepository additionalAmountRepository;

    public AdditionalAmountServiceImpl(AdditionalAmountRepository additionalAmountRepository) {
        this.additionalAmountRepository = additionalAmountRepository;
    }

    public AdditionalAmount create(AdditionalAmount additionalAmount) {
        return additionalAmountRepository.save(additionalAmount);
    }

    public List<AdditionalAmount> findAll() {
        List<AdditionalAmount> additionalAmounts = additionalAmountRepository.findAll();
        return additionalAmounts;
    }

    public Optional<AdditionalAmount> findById(Long id) {
        return additionalAmountRepository.findById(id);
    }

    public void delete(Long id) {
        Optional<AdditionalAmount> op = additionalAmountRepository.findById(id);
        if (op.isEmpty()) {
            throw new NotFoundException(id);
        }

        additionalAmountRepository.deleteById(id);
    }
}
