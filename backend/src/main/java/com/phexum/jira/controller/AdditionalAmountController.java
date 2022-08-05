package com.phexum.jira.controller;

import com.phexum.jira.service.AdditionalAmountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/additional")
public class AdditionalAmountController {
    private final AdditionalAmountService additionalAmountService;

    public AdditionalAmountController(AdditionalAmountService additionalAmountService) {
        this.additionalAmountService = additionalAmountService;
    }
}
