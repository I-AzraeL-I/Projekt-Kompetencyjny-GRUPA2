package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private Long scheduleId;

    private LocalTime start;

    private LocalTime end;

    private List<Integer> days;

}