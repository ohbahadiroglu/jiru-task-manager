package com.phexum.jira.entity;

import lombok.Getter;
import lombok.Setter;

import javax.lang.model.type.PrimitiveType;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class AdditionalAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "name should not be blank")
    private String name;
    @NotNull(message = "amount should not be blank")
    private double amount;

    @NotNull
    @ManyToOne
    private Period period;

}
