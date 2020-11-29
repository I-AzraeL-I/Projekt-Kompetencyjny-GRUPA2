package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDTO {

    private Long subjectId;

    private String subjectName;
}
