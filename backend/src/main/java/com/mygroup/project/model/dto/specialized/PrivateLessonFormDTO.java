package com.mygroup.project.model.dto.specialized;

import com.mygroup.project.model.dto.basic.SubjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PrivateLessonFormDTO {

    private LocalDate privateLessonDate;

    private LocalTime privateLessonStartHour;

    private LocalTime privateLessonEndHour;

    private Long tutorId;

    private Long studentId;

    private Long subjectId;

}
