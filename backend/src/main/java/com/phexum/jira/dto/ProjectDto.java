package com.phexum.jira.dto;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private String key;
    private String name;

    public static ProjectDto from(BasicProject basicProject) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setKey(basicProject.getKey());
        projectDto.setName(basicProject.getName());
        return projectDto;
    }
}
