package com.phexum.jira.controller;

import com.phexum.jira.repository.JiraClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
public class ExampleController {

    @Value("${jira.username}")
    private String username;
    @Value("${jira.password}")
    private String password;

    @GetMapping
    public ResponseEntity projects() throws ExecutionException, InterruptedException {
        JiraClient jiraClient = new JiraClient(username, password, "https://tunesoft.atlassian.net");
        jiraClient.getAllProjects().forEach(p -> System.out.println(p.getKey()));

        jiraClient.getAllIssues("TN").forEach(i -> {


            System.out.println(i.getDescription());

            System.out.println(i.getLabels());

        });
        return ResponseEntity.ok().build();

    }
}
