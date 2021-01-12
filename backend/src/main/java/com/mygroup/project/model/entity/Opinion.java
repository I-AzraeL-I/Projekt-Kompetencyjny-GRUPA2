package com.mygroup.project.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long opinionId;

    @Column(nullable = false, length = 250)
    private String comment;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(optional = false)
    private Tutor tutor;

}
