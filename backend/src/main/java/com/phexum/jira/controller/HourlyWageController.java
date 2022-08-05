package com.phexum.jira.controller;

import com.phexum.jira.entity.HourlyWage;
import com.phexum.jira.entity.Site;
import com.phexum.jira.service.HourlyWageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/wage")
public class HourlyWageController {
    private final HourlyWageService hourlyWageService;

    public HourlyWageController(HourlyWageService hourlyWageService) {
        this.hourlyWageService = hourlyWageService;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody HourlyWage hourlyWage) {

        Optional<HourlyWage> op = Optional.ofNullable(hourlyWageService.create(hourlyWage));
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(op.get());
    }
}
