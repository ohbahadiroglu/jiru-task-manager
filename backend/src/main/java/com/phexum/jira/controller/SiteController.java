package com.phexum.jira.controller;

import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.service.SiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/site")

public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Site site) {
        return ResponseEntity.ok(siteService.create(site));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Site site) {
        return ResponseEntity.ok(siteService.update(site));
    }

    @GetMapping
    public ResponseEntity<List<Site>> findAll() {
        return ResponseEntity.ok(siteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Optional<Site> op = this.siteService.findById(id);
        if (op.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(op.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            siteService.delete(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
