package com.phexum.jira.controller;

import com.phexum.jira.entity.Site;
import com.phexum.jira.service.SiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site")

public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }
    @PostMapping("/add")
    public ResponseEntity<Site> addSite (@RequestBody Site site){

        Site siteNew = this.siteService.addSite(site);
        return  new ResponseEntity<Site>(siteNew, HttpStatus.CREATED);
    }
}
