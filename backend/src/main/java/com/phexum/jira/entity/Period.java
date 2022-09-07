package com.phexum.jira.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.phexum.jira.FaturaState;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private FaturaState state;

    private double cost;

    @ManyToOne
    private HourlyWage hourlyWage;
}
