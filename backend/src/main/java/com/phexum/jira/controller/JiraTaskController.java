package com.phexum.jira.controller;

import com.phexum.jira.service.JiraTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/jiraTask")
public class JiraTaskController {
    private final JiraTaskService jiraTaskService;


    public JiraTaskController(JiraTaskService jiraTaskService) {
        this.jiraTaskService = jiraTaskService;
    }

    @GetMapping
    public ResponseEntity getAllIssues(String projectKey, long siteId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(jiraTaskService.getAllIssues(projectKey, siteId));
    }
}
