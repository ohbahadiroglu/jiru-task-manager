package com.phexum.jira.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.Getter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class JiraClient {
    private final String username;
    private final String password;
    private final String jiraUrl;
    private final JiraRestClient restClient;

    public JiraClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    public Iterable<BasicProject> getAllProjects() throws ExecutionException, InterruptedException {
        return restClient.getProjectClient().getAllProjects().get();
    }

    public List<Issue> getAllIssues(String project) throws ExecutionException, InterruptedException {
        List<Issue> issues = new ArrayList<>();
        AtomicInteger offset = new AtomicInteger();
        while (true) {
            Iterable<Issue> temp = getIssues("TN", null, 200, offset.get());
            if (!temp.iterator().hasNext()) {
                break;
            }
            temp.forEach(t -> {
                offset.getAndIncrement();
                issues.add(t);
            });

        }
        return issues;
    }


    public List<Issue> getAllDoneIssues(String project) throws ExecutionException, InterruptedException {
        List<Issue> issues = new ArrayList<>();
        AtomicInteger offset = new AtomicInteger();
        while (true) {
            Iterable<Issue> temp = getIssues("TN", "status='DONE'", 200, offset.get());
            if (!temp.iterator().hasNext()) {
                break;
            }
            temp.forEach(t -> {
                offset.getAndIncrement();
                issues.add(t);
            });

        }
        return issues;
    }

    public Iterable<Issue> getIssues(String project, String query, int limit, int offset) throws ExecutionException, InterruptedException {
        return restClient.getSearchClient().searchJql("project=" + project + (query == null ? "" : " AND " + query), limit, offset, Set.of("worklog", "summary", "status", "key", "description", "issuetype", "created", "updated", "project","reporter","assignee","labels")).get().getIssues();
    }

}

