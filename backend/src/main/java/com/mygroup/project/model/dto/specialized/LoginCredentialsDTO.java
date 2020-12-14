package com.mygroup.project.model.dto.specialized;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginCredentialsDTO {

    @JsonProperty("emailAddress")
    private String email;

    @JsonProperty("plainPassword")
    private String password;
}
