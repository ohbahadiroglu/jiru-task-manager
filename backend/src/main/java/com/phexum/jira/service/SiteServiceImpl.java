package com.phexum.jira.service;

import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.Session;
import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.JiraClient;
import com.phexum.jira.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteServiceImpl implements SiteService {
    private final SiteRepository siteRepository;

    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Site create(Site site) throws RestClientException {
        try{
            JiraClient jiraClient = new JiraClient(site.getEmail(), site.getToken(), site.getUrl());
            Session x =  jiraClient.getRestClient().getSessionClient().getCurrentSession().claim();
            return siteRepository.save(site);

        } catch (RestClientException e){
            if(e.getMessage().equals("org.codehaus.jettison.json.JSONException: JSONObject[\"loginInfo\"] not found.")){
                return siteRepository.save(site);
            }
            throw e ;

        }
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

    @Override
    public Site update(Site site) {

        Optional<Site> op = siteRepository.findById(site.getId());
        if (op.isEmpty()) {
            throw new NotFoundException(site.getId());
        }
        return siteRepository.save(site);
    }
}
