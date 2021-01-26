package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpinionDTO {

    private Long opinionId;

    private String comment;

    private Integer rating;

    private Long tutorId;

}
