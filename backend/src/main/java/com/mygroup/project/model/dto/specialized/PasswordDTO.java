package com.mygroup.project.model.dto.specialized;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PasswordDTO {

    @NotBlank
    @Length(min = 8, max = 50)
    String plainPassword;
}
