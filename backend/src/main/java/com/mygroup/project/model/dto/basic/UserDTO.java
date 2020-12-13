package com.mygroup.project.model.dto.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mygroup.project.model.dto.View;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    @JsonView(View.Minimal.class)
    private Long userId;

    @JsonView(View.Minimal.class)
    private String firstName;

    @JsonView(View.Minimal.class)
    private String lastName;

    @JsonView(View.Minimal.class)
    private LocalDate birthDate;

    @JsonView(View.Minimal.class)
    private String description;

    private AddressDTO address;

    private ContactDTO contact;

    private Set<UserSubjectDTO> userSubjects;

    @JsonIgnore
    private String password;

    public void addUserSubject(UserSubjectDTO userSubjectDTO) {
        userSubjects.add(userSubjectDTO);
    }

    public void removeUserSubject(UserSubjectDTO userSubjectDTO) {
        userSubjects.remove(userSubjectDTO);
    }
}
