package com.phexum.jira.service;

import com.phexum.jira.dto.IssueDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface JiraTaskService {
    List<IssueDto> getAllIssues(String projectKey, long siteId) throws ExecutionException, InterruptedException;
}
