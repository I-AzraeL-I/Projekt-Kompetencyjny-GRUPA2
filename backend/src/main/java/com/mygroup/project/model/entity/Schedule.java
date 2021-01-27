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

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean monday;

    private boolean tuesday;

    private boolean wednesday;

    private boolean thursday;

    private boolean friday;

    private boolean saturday;

    private boolean sunday;

}
