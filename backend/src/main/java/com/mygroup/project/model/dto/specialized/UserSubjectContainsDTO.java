package com.mygroup.project.model.dto.specialized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubjectContainsDTO {

    private Long subjectId;

    private String subjectName;

    private boolean isChosen;
}
