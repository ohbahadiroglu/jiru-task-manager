package com.phexum.jira.service;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.phexum.jira.dto.IssueDto;
import com.phexum.jira.entity.Site;
import com.phexum.jira.entity.Task;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.JiraClient;
import com.phexum.jira.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JiraTaskServiceImpl implements JiraTaskService {
    private final TaskService taskService;
    private final SiteService siteService;
    private final TaskRepository taskRepository;

    public JiraTaskServiceImpl(TaskService taskService, SiteService siteService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.siteService = siteService;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<IssueDto> getAllIssues(String projectKey, long siteId) throws ExecutionException, InterruptedException {
        Optional<Site> siteOp = siteService.findById(siteId);
        if (siteOp.isEmpty()) {
            throw new NotFoundException(siteId);
        }
        Site site = siteOp.get();
        JiraClient jiraClient = new JiraClient(site.getEmail(), site.getToken(), site.getUrl());
        List<IssueDto> issues = jiraClient.getAllIssues(projectKey).stream().map(IssueDto::from).collect(Collectors.toList());
        List<IssueDto> issuesReturn = new ArrayList<>();


        for (IssueDto issue : issues) {
            Optional<Task> optionalTask = taskRepository.findByKey(issue.getKey());
            if (optionalTask.isPresent()) {
                if (Objects.equals(issue.getStatus(), "Done")) {
                    Task tempTask = optionalTask.get();
                    taskService.update(tempTask, issue.getSummary(), issue.getTotalWorkHours(), issue.getDescription());
                } else {
                    taskService.delete(optionalTask.get().getId());
                }
            } else {
                issuesReturn.add(issue);
            }
        }
        return issuesReturn;
    }

}
