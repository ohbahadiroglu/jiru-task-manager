package com.phexum.jira.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String summary;
    @NotNull
    private float totalHours;
    @NotBlank
    private String key;

    @NotNull
    @ManyToOne
    private Period period;

    @Lob
    @Column(length=2147483647)
    private String description;

}
