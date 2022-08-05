package com.phexum.jira.service;

import com.phexum.jira.entity.Site;

import java.util.List;
import java.util.Optional;


public interface SiteService {
    Site create(Site site);

    List<Site> findAll();

    Optional<Site> findById(Long id);

    void delete(Long id);
}
