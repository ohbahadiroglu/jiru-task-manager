package com.phexum.jira.repository;

import com.phexum.jira.entity.Site;
import com.phexum.jira.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
