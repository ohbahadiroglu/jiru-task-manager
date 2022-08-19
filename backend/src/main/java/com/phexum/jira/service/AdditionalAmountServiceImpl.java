package com.phexum.jira.service;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.AdditionalAmountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdditionalAmountServiceImpl implements AdditionalAmountService {
    private final AdditionalAmountRepository additionalAmountRepository;
    private final PeriodService periodService;

    public AdditionalAmountServiceImpl(AdditionalAmountRepository additionalAmountRepository, PeriodService periodService) {
        this.additionalAmountRepository = additionalAmountRepository;
        this.periodService = periodService;
    }

    public AdditionalAmount create(AdditionalAmount additionalAmount) {
        additionalAmountRepository.save(additionalAmount);
        periodService.periodCostUpdate(additionalAmount.getPeriod().getId());
        return additionalAmount;

    }

    public AdditionalAmount update(long id, double amount, String name) {
        AdditionalAmount additionalAmount = additionalAmountRepository.findById(id).get();
        additionalAmount.setAmount(amount);
        additionalAmount.setName(name);
        additionalAmountRepository.save(additionalAmount);
        periodService.periodCostUpdate(additionalAmount.getPeriod().getId());
        return additionalAmount;
    }

    public List<AdditionalAmount> findAll() {
        return additionalAmountRepository.findAll();
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
        periodService.periodCostUpdate(op.get().getPeriod().getId());
    }
}
