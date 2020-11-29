package com.mygroup.project.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mygroup.project.model.entity.Role;
import com.mygroup.project.model.entity.Subject;
import com.mygroup.project.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSubjectDTO {

    private Long userSubjectId;

    private User user;

    private Subject subject;

    private Role role;
}
