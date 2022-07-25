package com.phexum.jira.repository;

import com.phexum.jira.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Url, Long> {
}
