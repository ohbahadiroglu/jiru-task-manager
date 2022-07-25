package com.phexum.jira.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.util.concurrent.ExecutionException;

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

    public Iterable<Issue> getAllIssues(String project) throws ExecutionException, InterruptedException {
        return  restClient.getSearchClient().searchJql("project="+project).get().getIssues();
    }
}

