package com.phexum.jira.controller;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.service.HourlyWageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wage")
public class HourlyWageController {
    private final HourlyWageService hourlyWageService;

    public HourlyWageController(HourlyWageService hourlyWageService) {
        this.hourlyWageService = hourlyWageService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody HourlyWage hourlyWage) throws Exception {
        return ResponseEntity.ok(hourlyWageService.create(hourlyWage));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody HourlyWage hourlyWage) {
        return ResponseEntity.ok(hourlyWageService.update(hourlyWage.getId(),hourlyWage.getAmount(), hourlyWage.getName()));
    }

    @GetMapping
    public ResponseEntity<List<HourlyWage>> findAll() {
        return ResponseEntity.ok(hourlyWageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<HourlyWage> op = hourlyWageService.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(op.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            hourlyWageService.delete(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}