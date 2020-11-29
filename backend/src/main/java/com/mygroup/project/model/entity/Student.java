package com.mygroup.project.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long studentId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "student")
    private Set<PrivateLesson> privateLessons = new HashSet<>();
}
