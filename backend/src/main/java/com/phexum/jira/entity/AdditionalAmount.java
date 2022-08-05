package com.phexum.jira.entity;

import lombok.Getter;
import lombok.Setter;

import javax.lang.model.type.PrimitiveType;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class AdditionalAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double amount;

    @ManyToOne
    private Period period;

}
