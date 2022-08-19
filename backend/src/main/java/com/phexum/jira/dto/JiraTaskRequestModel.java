package com.phexum.jira.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JiraTaskRequestModel {
    private String projectKey;
    private long siteId;
}
