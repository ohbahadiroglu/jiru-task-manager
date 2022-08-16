package com.phexum.jira.service;

import com.phexum.jira.dto.ProjectDto;

import java.util.concurrent.ExecutionException;

public interface ProjectService {
    Iterable<ProjectDto> findBySite(long siteId) throws ExecutionException, InterruptedException;
}
