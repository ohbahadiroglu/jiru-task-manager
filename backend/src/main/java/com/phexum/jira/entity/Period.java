package com.phexum.jira.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name can not be Blank")
    private String name;
    @NotBlank(message = "State can not be Blank")
    private String state;
    private double cost;

    @ManyToOne
    private HourlyWage hourlyWage;
}
