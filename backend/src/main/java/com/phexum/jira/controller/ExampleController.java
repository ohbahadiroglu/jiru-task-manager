package com.phexum.jira.controller;

import com.phexum.jira.dto.IssueDto;
import com.phexum.jira.repository.JiraClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class ExampleController {

    @Value("${jira.username}")
    private String username;
    @Value("${jira.password}")
    private String password;

    @GetMapping
    public ResponseEntity projects() throws ExecutionException, InterruptedException {
        JiraClient jiraClient = new JiraClient(username, password, "https://bocek.atlassian.net");
        jiraClient.getAllProjects().forEach(p -> System.out.println(p.getKey()));
        List<IssueDto> issues = jiraClient.getAllDoneIssues("JR").stream().map(IssueDto::from).collect(Collectors.toList());

        AtomicInteger count = new AtomicInteger();

        for (IssueDto i : issues) {
            System.out.println(i.getKey());
            System.out.println(i.getSummary());
            System.out.println(i.getStatus());
            System.out.println("Total Work Hours: " + i.getTotalWorkHours());
            System.out.println("-----------------------------------------------------");
            count.getAndIncrement();
        }
        System.out.println(count.get());


        return ResponseEntity.ok(issues);
    }
}
