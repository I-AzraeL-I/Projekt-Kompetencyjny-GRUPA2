package com.mygroup.project.controller;

import com.mygroup.project.model.dto.Roles;
import com.mygroup.project.model.dto.basic.OpinionDTO;
import com.mygroup.project.model.dto.basic.PrivateLessonDTO;
import com.mygroup.project.model.dto.basic.SubjectDTO;
import com.mygroup.project.model.dto.basic.TutorDTO;
import com.mygroup.project.model.dto.specialized.OpinionFormDTO;
import com.mygroup.project.model.dto.specialized.PrivateLessonFormDTO;
import com.mygroup.project.model.dto.specialized.UserSubjectOfferDTO;
import com.mygroup.project.model.service.OpinionServiceImpl;
import com.mygroup.project.model.service.PrivateLessonServiceImpl;
import com.mygroup.project.model.service.TutorServiceImpl;
import com.mygroup.project.model.service.UserSubjectServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.net.URI;

@RestController
public class ApplicationController {

    private final UserSubjectServiceImpl userSubjectService;
    private final TutorServiceImpl tutorService;
    private final ModelMapper modelMapper;
    private final OpinionServiceImpl opinionService;
    private final PrivateLessonServiceImpl privateLessonService;

    public ApplicationController(UserSubjectServiceImpl userSubjectService, TutorServiceImpl tutorService, ModelMapper modelMapper, OpinionServiceImpl opinionService, PrivateLessonServiceImpl privateLessonService) {
        this.userSubjectService = userSubjectService;
        this.tutorService = tutorService;
        this.modelMapper = modelMapper;
        this.opinionService = opinionService;
        this.privateLessonService = privateLessonService;
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
    public ResponseEntity<TutorDTO> getOffer(@PathVariable("id") Long userId) {
        TutorDTO tutorDTO = tutorService.getByUserId(userId);
        return ResponseEntity.ok(tutorDTO);
    }

    @PostMapping("/addOpinion")
    public ResponseEntity<?> addOpinion(@Valid @RequestBody OpinionFormDTO opinionFormDTO) {
        OpinionDTO opinionDTO = modelMapper.map(opinionFormDTO, OpinionDTO.class);
        opinionDTO = opinionService.create(opinionDTO);
        return ResponseEntity.created(URI.create("/opinion/" + opinionDTO.getOpinionId())).headers(new HttpHeaders()).body(opinionDTO.getOpinionId());
    }

    @GetMapping("/studentHistory/{id}")
    public Collection<PrivateLessonDTO> getStudentHistory(@PathVariable("id") Long id) {
        return privateLessonService.getByStudentId(id);
    }

    @GetMapping("/tutorHistory/{id}")
    public Collection<PrivateLessonDTO> getTutorHistory(@PathVariable("id") Long id) {
        return privateLessonService.getByTutorId(id);
    }

    @PostMapping("/addPrivateLesson")
    public ResponseEntity<?> addLesson(@Valid @RequestBody PrivateLessonFormDTO privateLessonFormDTO) {
        PrivateLessonDTO privateLessonDTO = modelMapper.map(privateLessonFormDTO, PrivateLessonDTO.class);
        privateLessonDTO.setAcceptance(0);
        privateLessonDTO.setPrice(100);
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubjectId(privateLessonFormDTO.getSubjectId());
        privateLessonDTO.setSubject(subjectDTO);
        privateLessonDTO = privateLessonService.create(privateLessonDTO);
        return ResponseEntity.created(URI.create("/addPrivateLesson/")).headers(new HttpHeaders()).body(privateLessonDTO.getPrivateLessonId());
    }

    @PostMapping("/updatePrivateLesson")
    public ResponseEntity<?> updateLesson(@Valid @RequestBody PrivateLessonFormDTO privateLessonFormDTO) {
        PrivateLessonDTO privateLessonDTO = modelMapper.map(privateLessonFormDTO, PrivateLessonDTO.class);
        privateLessonDTO = privateLessonService.update(privateLessonDTO);
        return ResponseEntity.created(URI.create("/updatePrivateLesson/")).headers(new HttpHeaders()).body(privateLessonDTO.getPrivateLessonId());
    }

}
