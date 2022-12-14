package com.phexum.jira.controller;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.service.PeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/period")
public class PeriodController {
    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Period period) {
        return ResponseEntity.ok(periodService.create(period));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Period period) {
        return ResponseEntity.ok(periodService.update(period.getId(), period.getName(),period.getState(), period.getHourlyWage().getId()));
    }

    @GetMapping
    public ResponseEntity<List<Period>> findAll() {
        return ResponseEntity.ok(periodService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<Period> op = periodService.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(op.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            periodService.delete(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
