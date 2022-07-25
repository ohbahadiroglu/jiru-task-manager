package com.phexum.jira.service;

import com.phexum.jira.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository repository;


    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }
}
