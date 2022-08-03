package com.phexum.jira.service;

import com.phexum.jira.entity.Site;
import com.phexum.jira.repository.SiteRepository;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImp implements SiteService{
    private final SiteRepository siteRepository;

    public SiteServiceImp(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }


    public Site addSite(Site site) {
        site = siteRepository.save(site);
       return site;
    }


}
