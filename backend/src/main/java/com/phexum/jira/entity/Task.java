package com.phexum.jira.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    private String type;
    private int totalHours;
    private String code;

    @ManyToOne
    private Period period;

}
