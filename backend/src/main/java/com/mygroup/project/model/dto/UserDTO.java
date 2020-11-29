package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    @JsonView({View.MinimalGET.class, View.RegisterPOST.class})
    private Long userId;

    @JsonView({View.MinimalGET.class, View.RegisterPOST.class})
    private String firstName;

    @JsonView({View.MinimalGET.class, View.RegisterPOST.class})
    private String lastName;

    @JsonView({View.ExtendedGET.class, View.RegisterPOST.class})
    private LocalDate birthDate;

    @JsonView({View.MinimalGET.class, View.UserSettingsPUT.class})
    private String description;

    @JsonView(View.MinimalGET.class)
    private AddressDTO address;

    @JsonView(View.ExtendedGET.class)
    private ContactDTO contact;

    @JsonIgnore
    private String password;
}
