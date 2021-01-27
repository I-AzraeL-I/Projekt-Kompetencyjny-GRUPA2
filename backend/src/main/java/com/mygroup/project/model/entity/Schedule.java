package com.mygroup.project.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long scheduleId;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Boolean monday;

    @Column(nullable = false)
    private Boolean tuesday;

    @Column(nullable = false)
    private Boolean wednesday;

    @Column(nullable = false)
    private Boolean thursday;

    @Column(nullable = false)
    private Boolean friday;

    @Column(nullable = false)
    private Boolean saturday;

    @Column(nullable = false)
    private Boolean sunday;

}
