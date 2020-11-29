package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {

    @JsonView({View.ExtendedGET.class, View.LoginPOST.class})
    private String emailAddress;

    @JsonView(View.ExtendedGET.class)
    private String phoneNumber;
}
