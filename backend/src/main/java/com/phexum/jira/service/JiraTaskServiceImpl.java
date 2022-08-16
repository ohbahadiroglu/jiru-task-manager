package com.phexum.jira.service;

import com.phexum.jira.dto.IssueDto;
import com.phexum.jira.entity.Site;
import com.phexum.jira.entity.Task;
import com.phexum.jira.repository.JiraClient;
import com.phexum.jira.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JiraTaskServiceImpl implements JiraTaskService {
    private final TaskService taskService;
    private final SiteService siteService;

    public JiraTaskServiceImpl(TaskService taskService, SiteService siteService) {
        this.taskService = taskService;
        this.siteService = siteService;
    }

    @Override
    public List<IssueDto> getAllIssues(String projectKey, long siteId) throws ExecutionException, InterruptedException {
        Optional<Site> siteOp = siteService.findById(siteId);
        Site site = siteOp.get();
        JiraClient jiraClient = new JiraClient(site.getEmail(), site.getToken(), site.getUrl());
        List<IssueDto> issues = jiraClient.getAllIssues(projectKey).stream().map(IssueDto::from).collect(Collectors.toList());
        for (IssueDto issue : issues) {
            Optional<Task> optionalTask = taskService.findByCode(issue.getKey()); // taskServicten çagir.
            if ( optionalTask.isPresent() ) {
                if (Objects.equals(issue.getStatus(), "Done")){
                    Task tempTask = optionalTask.get();
                    tempTask.setSummary(issue.getSummary());
                    tempTask.setTotalHours(issue.getTotalWorkHours());
                    taskService.create(tempTask);
                }else{
                    taskService.delete(taskService.findByCode(issue.getKey()).get().getId());
                }
            }
        }
        return issues;
    }

}
