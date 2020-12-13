package com.mygroup.project.model.dto.basic;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddressDTO {

    @NotBlank
    @Length(max = 50)
    private String city;

    @NotBlank
    @Length(max = 50)
    private String street;
}
