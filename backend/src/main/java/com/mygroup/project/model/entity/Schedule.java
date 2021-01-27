package com.mygroup.project.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long scheduleId;

    @Column(nullable = false)
    private LocalTime start;

    @Column(nullable = false)
    private LocalTime end;

    @ElementCollection
    private List<Integer> days;

}
