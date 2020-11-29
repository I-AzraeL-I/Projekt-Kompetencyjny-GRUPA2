package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    @JsonView(View.MinimalGET.class)
    private String city;

    @JsonView(View.CompleteGET.class)
    private String street;
}
