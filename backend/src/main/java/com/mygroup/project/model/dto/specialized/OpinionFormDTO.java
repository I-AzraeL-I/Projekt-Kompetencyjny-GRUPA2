package com.mygroup.project.model.dto.specialized;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OpinionFormDTO {

    @NotBlank
    @Length(max = 255)
    private String comment;

    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    private Integer rating;

    @NotNull
    private Long tutorId;

}
