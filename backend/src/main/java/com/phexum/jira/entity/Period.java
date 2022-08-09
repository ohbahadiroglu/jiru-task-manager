package com.phexum.jira.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name can not be Blank")
    private String name;
    @NotBlank(message = "State can not be Blank")
    private String state;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private HourlyWage hourlyWage;
}
