package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSubjectDTO {

    private Long userSubjectId;

    private Long userId;

    private Long subjectId;

    private String subjectName;

    private Long roleId;
}
