package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentDTO {

    @JsonView(View.MinimalGET.class)
    private Long studentId;

    @JsonView(View.MinimalGET.class)
    private UserDTO user;

    @JsonView(View.AlternateUserGET.class)
    private Set<PrivateLessonDTO> privateLessons = new HashSet<>();
}
