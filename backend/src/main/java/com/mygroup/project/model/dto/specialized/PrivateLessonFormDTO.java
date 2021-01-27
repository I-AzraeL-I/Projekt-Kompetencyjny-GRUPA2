package com.mygroup.project.model.dto.specialized;

import com.mygroup.project.model.dto.basic.SubjectDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrivateLessonFormDTO {

    private LocalDate privateLessonDate;

    private LocalTime privateLessonStartHour;

    private LocalTime privateLessonEndHour;

    private Long tutorId;

    private Long studentId;

    private SubjectDTO subject;

}
