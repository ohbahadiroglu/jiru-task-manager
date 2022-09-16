package com.phexum.jira.controller;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.entity.Period;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.AdditionalAmountRepository;
import com.phexum.jira.service.AdditionalAmountService;
import com.phexum.jira.service.PeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/additional")
public class AdditionalAmountController {

    private final AdditionalAmountService additionalAmountService;
    private final AdditionalAmountRepository additionalAmountRepository;
    private final PeriodService periodService;

    public AdditionalAmountController(AdditionalAmountService additionalAmountService, AdditionalAmountRepository additionalAmountRepository, PeriodService periodService) {
        this.additionalAmountService = additionalAmountService;
        this.additionalAmountRepository = additionalAmountRepository;
        this.periodService = periodService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AdditionalAmount additionalAmount) {
        return ResponseEntity.ok(additionalAmountService.create(additionalAmount));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody AdditionalAmount additionalAmount) {
        return ResponseEntity.ok(additionalAmountService.update(additionalAmount.getId(), additionalAmount.getAmount(), additionalAmount.getName()));
    }

    @GetMapping
    public ResponseEntity<List<AdditionalAmount>> findAll() {
        return ResponseEntity.ok(additionalAmountService.findAll());
    }

    @GetMapping("period-additional/{periodId}")
    public ResponseEntity findByPeriod(@PathVariable("periodId") long periodId) {
        Optional<Period> op = periodService.findById(periodId);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(additionalAmountRepository.findByPeriod(op.get()));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<AdditionalAmount> op = additionalAmountService.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(op.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            additionalAmountService.delete(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
