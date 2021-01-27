package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

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