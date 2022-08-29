package com.phexum.jira.controller;

import com.phexum.jira.service.JiraTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/jiraTask")
public class JiraTaskController {
    private final JiraTaskService jiraTaskService;


    public JiraTaskController(JiraTaskService jiraTaskService) {
        this.jiraTaskService = jiraTaskService;
    }

    @GetMapping("/{siteId}/{projectKey}")
    public ResponseEntity getAllIssues(@PathVariable("siteId") long siteId,@PathVariable("projectKey") String projectKey) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(jiraTaskService.getAllIssues(projectKey,siteId));
    }
}
