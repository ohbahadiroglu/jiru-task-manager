package com.phexum.jira.repository;

import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByKey(String key);

    List<Task> findByPeriod(Period period);

}
