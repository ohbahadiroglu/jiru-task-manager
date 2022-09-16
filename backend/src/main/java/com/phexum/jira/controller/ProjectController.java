package com.phexum.jira.controller;

import com.phexum.jira.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{siteId}")
    public ResponseEntity projects(@PathVariable long siteId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(projectService.findBySite(siteId));
    }
}
