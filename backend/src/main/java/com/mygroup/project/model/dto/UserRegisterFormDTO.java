package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class UserRegisterFormDTO {

    @NotBlank
    @Length(max = 50)
    @JsonView(View.RegisterPOST.class)
    private String firstName;

    @NotBlank
    @Length(max = 50)
    @JsonView(View.RegisterPOST.class)
    private String lastName;

    @NotNull
    @JsonView(View.RegisterPOST.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    private LocalDate birthDate;

    @NotBlank
    @Length(max = 50)
    @JsonView(View.RegisterPOST.class)
    private String city;

    @NotBlank
    @Length(max = 50)
    @JsonView(View.RegisterPOST.class)
    private String street;

    @NotBlank
    @Length(max = 50)
    @Email
    @JsonView(View.LoginPOST.class)
    private String emailAddress;

    @NotBlank
    @Pattern(regexp="[\\d]{9}")
    @JsonView(View.RegisterPOST.class)
    private String phoneNumber;

    @NotBlank
    @Length(min = 8, max = 50)
    @JsonView(View.LoginPOST.class)
    private String plainPassword;
}
