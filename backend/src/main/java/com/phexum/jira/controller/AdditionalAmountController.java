package com.phexum.jira.controller;

import com.phexum.jira.entity.AdditionalAmount;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.service.AdditionalAmountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/additional")
public class AdditionalAmountController {

    private final AdditionalAmountService additionalAmountService;

    public AdditionalAmountController(AdditionalAmountService additionalAmountService) {
        this.additionalAmountService = additionalAmountService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AdditionalAmount additionalAmount) {
        return ResponseEntity.ok(additionalAmountService.create(additionalAmount));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody AdditionalAmount additionalAmount) {
        return ResponseEntity.ok(additionalAmountService.update(additionalAmount.getId(),additionalAmount.getAmount(),additionalAmount.getName()));
    }

    @GetMapping
    public ResponseEntity<List<AdditionalAmount>> findAll() {
        return ResponseEntity.ok(additionalAmountService.findAll());
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
