package com.mygroup.project.controller;

import com.mygroup.project.model.dto.specialized.UserSubjectOfferDTO;
import com.mygroup.project.model.service.UserSubjectServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ApplicationController {

    private final UserSubjectServiceImpl userSubjectService;
    private final ModelMapper modelMapper;
    private final String teacherRole = "ROLE_TEACHER";

    public ApplicationController(ModelMapper modelMapper, UserSubjectServiceImpl userSubjectService) {
        this.userSubjectService = userSubjectService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getLessons")
    public Collection<UserSubjectOfferDTO> getOffers() {
        return userSubjectService.getAllByRole(teacherRole);
    }

    @GetMapping("/")
    public ResponseEntity<Void> getMain() {
        return ResponseEntity.ok().build();
    }
}
