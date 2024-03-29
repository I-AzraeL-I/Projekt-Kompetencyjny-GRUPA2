package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentDTO {

    private Long studentId;

    private UserDTO user;

    private Set<PrivateLessonDTO> privateLessons = new HashSet<>();
}
