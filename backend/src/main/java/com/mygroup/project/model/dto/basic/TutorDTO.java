package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TutorDTO {

    private Long tutorId;

    private UserDTO user;

    private Set<PrivateLessonDTO> privateLessons = new HashSet<>();

    private List<OpinionDTO> opinions = new ArrayList<>();

}
