package com.phexum.jira.controller;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.phexum.jira.repository.JiraClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

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
        List<Issue> issues = jiraClient.getAllDoneIssues("TN");

        AtomicInteger count = new AtomicInteger();


        for (Issue i : issues) {
            System.out.println(i.getKey());
            System.out.println(i.getSummary());
            System.out.println(i.getStatus().getName());
            System.out.println(i.getLabels());

            float totalMinutes = 0F;
            for (Worklog w : i.getWorklogs()) {
                System.out.println(w.getMinutesSpent() + " : " + w.getComment());
                totalMinutes += w.getMinutesSpent();
            }
            System.out.println("Total Work Hours: " + (totalMinutes / 60F));

            System.out.println("-----------------------------------------------------");
            count.getAndIncrement();
        }
        System.out.println(count.get());

        return ResponseEntity.ok().build();

    }
}
