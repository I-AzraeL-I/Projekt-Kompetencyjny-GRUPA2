package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PrivateLessonDTO {

    private Long privateLessonId;

    private LocalDate privateLessonDate;

    private LocalTime privateLessonStartHour;

    private LocalTime privateLessonEndHour;

    private float price;

    private TutorDTO tutor;

    private StudentDTO student;

    private SubjectDTO subject;
}
