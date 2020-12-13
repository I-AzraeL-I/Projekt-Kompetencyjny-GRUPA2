package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ContactDTO {

    @NotBlank
    @Length(max = 50)
    @Email
    private String emailAddress;

    @NotBlank
    @Pattern(regexp="[\\d]{9}")
    private String phoneNumber;
}
