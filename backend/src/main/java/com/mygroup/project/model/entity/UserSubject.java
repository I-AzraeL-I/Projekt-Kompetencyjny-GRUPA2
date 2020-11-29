package com.mygroup.project.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long userSubjectId;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Subject subject;

    @ManyToOne(optional = false)
    private Role role;
}
