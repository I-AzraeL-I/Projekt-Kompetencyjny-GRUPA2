package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PrivateLessonDTO {

    @JsonView(View.MinimalGET.class)
    private Long privateLessonId;

    @JsonView({View.MinimalGET.class, View.PrivateLessonPOST.class})
    private LocalDate privateLessonDate;

    @JsonView({View.MinimalGET.class, View.PrivateLessonPOST.class})
    private LocalTime privateLessonStartHour;

    @JsonView({View.MinimalGET.class, View.PrivateLessonPOST.class})
    private LocalTime privateLessonEndHour;

    @JsonView({View.ExtendedGET.class, View.PrivateLessonPOST.class})
    private float price;

    @JsonView({View.ExtendedGET.class, View.PrivateLessonPOST.class})
    private TutorDTO tutor;

    @JsonView({View.ExtendedGET.class, View.PrivateLessonPOST.class})
    private StudentDTO student;

    @JsonView({View.ExtendedGET.class, View.PrivateLessonPOST.class})
    private SubjectDTO subject;
}
