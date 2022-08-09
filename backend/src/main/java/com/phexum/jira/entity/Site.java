package com.phexum.jira.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "URL can not be Blank")
    private String url;
    @NotBlank(message = "Token can not be Blank")
    private String token;
    @NotBlank(message = "Email can not be Blank")
    private String email;
}
