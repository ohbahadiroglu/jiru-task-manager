package com.phexum.jira.dto;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class IssueDto {
    private String key;
    private String summary;
    private String status;
    private Set<String> labels;
    private float totalWorkHours;
    private float totalWorkMinutes;

    public static IssueDto from(Issue issue) {
        IssueDto dto = new IssueDto();
        dto.setKey(issue.getKey());
        dto.setLabels(issue.getLabels());
        dto.setSummary(issue.getSummary());

        float totalMinutes = 0F;
        for (Worklog w : issue.getWorklogs()) {
            System.out.println(w.getMinutesSpent() + " : " + w.getComment());
            totalMinutes += w.getMinutesSpent();
        }

        dto.setTotalWorkHours(totalMinutes/60F);
        dto.setTotalWorkMinutes(totalMinutes);
        dto.setStatus(issue.getStatus().getName());
        return dto;
    }
}
