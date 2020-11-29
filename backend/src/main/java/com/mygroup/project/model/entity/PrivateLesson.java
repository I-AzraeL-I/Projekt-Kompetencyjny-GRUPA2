package com.mygroup.project.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class PrivateLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long privateLessonId;

    @Column(nullable = false)
    private LocalDate privateLessonDate;

    @Column(nullable = false)
    private LocalTime privateLessonStartHour;

    @Column(nullable = false)
    private LocalTime privateLessonEndHour;

    @Column(nullable = false)
    private float price;

    @ManyToOne(optional = false)
    private Tutor tutor;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Subject subject;
}
