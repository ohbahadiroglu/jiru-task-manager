package com.phexum.jira.service;

import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.JiraClient;
import com.phexum.jira.repository.SiteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteServiceImpl implements SiteService {
    private final SiteRepository siteRepository;

    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Site create(Site site) {
        return siteRepository.save(site);
    }

    public List<Site> findAll() {
        List<Site> site = siteRepository.findAll();
        return site;
    }

    public Optional<Site> findById(Long id) {
        return siteRepository.findById(id);
    }

    public void delete(Long id) {
        Optional<Site> op = siteRepository.findById(id);
        if (op.isEmpty()) {
            throw new NotFoundException(id);
        }

        siteRepository.deleteById(id);
    }
}
