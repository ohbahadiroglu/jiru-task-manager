package com.phexum.jira.dto;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueDto {
    private String key;
    private String summary;
    private String status;
    private String description;
    private float totalWorkHours;
    private float totalWorkMinutes;

    public static IssueDto from(Issue issue) {
        IssueDto dto = new IssueDto();
        dto.setKey(issue.getKey());
        dto.setDescription(issue.getDescription());
        dto.setSummary(issue.getSummary());

        float totalMinutes = 0F;
        for (Worklog w : issue.getWorklogs()) {
            totalMinutes += w.getMinutesSpent();
        }

        dto.setTotalWorkHours(totalMinutes / 60F);
        dto.setTotalWorkMinutes(totalMinutes);
        dto.setStatus(issue.getStatus().getName());
        return dto;
    }
}
