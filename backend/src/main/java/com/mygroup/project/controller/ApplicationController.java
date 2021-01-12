package com.mygroup.project.controller;

import com.mygroup.project.model.dto.Roles;
import com.mygroup.project.model.dto.basic.TutorDTO;
import com.mygroup.project.model.dto.specialized.UserSubjectOfferDTO;
import com.mygroup.project.model.service.TutorServiceImpl;
import com.mygroup.project.model.service.UserSubjectServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ApplicationController {

    private final UserSubjectServiceImpl userSubjectService;
    private final TutorServiceImpl tutorService;

    public ApplicationController(UserSubjectServiceImpl userSubjectService, TutorServiceImpl tutorService) {
        this.userSubjectService = userSubjectService;
        this.tutorService = tutorService;
    }

    @GetMapping("/")
    public ResponseEntity<Void> getMain() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getLessons")
    public Collection<UserSubjectOfferDTO> getOffers() {
        return userSubjectService.getAllByRole(Roles.ROLE_TEACHER.name());
    }

    @GetMapping("/getLessons/{id}")
    public ResponseEntity<TutorDTO> getOffer(@PathVariable("id") Long tutorId) {
        TutorDTO tutorDTO = tutorService.get(tutorId);
        return ResponseEntity.ok(tutorDTO);
    }

}
