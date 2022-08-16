package com.phexum.jira.service;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.phexum.jira.dto.ProjectDto;
import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.JiraClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final SiteService siteService;

    public ProjectServiceImpl(SiteService siteService) {
        this.siteService = siteService;
    }

    public List<ProjectDto> findBySite(long siteId) throws ExecutionException, InterruptedException {
        Optional<Site> siteOp = siteService.findById(siteId);
        if (siteOp.isEmpty()) {
            throw new NotFoundException("Site bulunamadı");
        }

        Site site = siteOp.get();

        JiraClient jiraClient = new JiraClient(site.getEmail(), site.getToken(), site.getUrl());
        List<BasicProject> basicProjectList = StreamSupport.stream((jiraClient.getAllProjects()).spliterator(), false).collect(Collectors.toList());
        return basicProjectList.stream().map(ProjectDto::from).collect(Collectors.toList());
    }
}
