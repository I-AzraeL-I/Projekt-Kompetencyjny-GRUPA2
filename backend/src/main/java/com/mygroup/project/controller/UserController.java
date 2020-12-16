package com.mygroup.project.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mygroup.project.model.dto.*;
import com.mygroup.project.model.dto.basic.*;
import com.mygroup.project.model.dto.specialized.PasswordDTO;
import com.mygroup.project.model.dto.specialized.UserSubjectContainsDTO;
import com.mygroup.project.model.service.RoleServiceImpl;
import com.mygroup.project.model.service.SubjectServiceImpl;
import com.mygroup.project.model.service.UserServiceImpl;
import com.mygroup.project.model.service.UserSubjectServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final SubjectServiceImpl subjectService;
    private final UserSubjectServiceImpl userSubjectService;
    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;

    public UserController(UserServiceImpl userService, SubjectServiceImpl subjectService,
                          UserSubjectServiceImpl userSubjectService, RoleServiceImpl roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.userSubjectService = userSubjectService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profil/{id}/data")
    @JsonView(View.Minimal.class)
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<UserDTO> getUserData(@PathVariable("id") Long userID) {
        UserDTO userDTO = userService.get(userID);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/profil/{id}/data")
    @JsonView(View.Minimal.class)
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<UserDTO> updateUserData(@PathVariable("id") Long userID, @Valid @RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/profil/{id}/subjects")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<Set<UserSubjectContainsDTO>> getUserSubjects(@PathVariable("id") Long userID) {
        Collection<UserSubjectDTO> userSubjectDTOs = userSubjectService.getAllByIdAndRole(userID, Roles.ROLE_TEACHER.name());
        Collection<SubjectDTO> subjectsOwned = modelMapper.map(userSubjectDTOs, new TypeToken<Set<SubjectDTO>>(){}.getType());
        List<SubjectDTO> subjects = new ArrayList<>(subjectService.getAll());
        subjects.removeAll(subjectsOwned);
        Set<UserSubjectContainsDTO> userSubjectContainsDTOs = new HashSet<>();
        subjects.forEach(e -> userSubjectContainsDTOs.add(
                new UserSubjectContainsDTO(e.getSubjectId(), e.getSubjectName(), false)));
        subjectsOwned.forEach(e -> userSubjectContainsDTOs.add(
                new UserSubjectContainsDTO(e.getSubjectId(), e.getSubjectName(), true)));

        return ResponseEntity.ok(userSubjectContainsDTOs);
    }

    @PutMapping("profil/{id}/subjects")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<UserSubjectContainsDTO> addOrDeleteUserSubject(@PathVariable("id") Long userID, @RequestBody UserSubjectContainsDTO userSubjectContainsDTO) {
        UserSubjectDTO userSubjectDTO = new UserSubjectDTO();
        userSubjectDTO.setSubjectId(userSubjectContainsDTO.getSubjectId());
        userSubjectDTO.setUserId(userID);
        userSubjectDTO.setRoleId(roleService.getByRoleName(Roles.ROLE_TEACHER.name()).getRoleId());
        if (userSubjectContainsDTO.isChosen()) {
            userService.addUserSubject(userSubjectDTO);
        } else {
            userService.removeUserSubject(userSubjectDTO);
        }
        return ResponseEntity.ok(userSubjectContainsDTO);
    }

    @GetMapping("/profil/{id}/contact")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<ContactDTO> getUserContact(@PathVariable("id") Long userID) {
        UserDTO userDTO = userService.get(userID);
        return ResponseEntity.ok(userDTO.getContact());
    }

    @PostMapping("/profil/{id}/contact")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<ContactDTO> updateUserContact(@PathVariable("id") Long userID, @Valid @RequestBody ContactDTO contactDTO) {
        userService.updateContact(userID, contactDTO);
        return ResponseEntity.ok(contactDTO);
    }

    @GetMapping("/profil/{id}/address")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<AddressDTO> getUserAddress(@PathVariable("id") Long userID) {
        UserDTO userDTO = userService.get(userID);
        return ResponseEntity.ok(userDTO.getAddress());
    }

    @PostMapping("/profil/{id}/address")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<AddressDTO> updateUserAddress(@PathVariable("id") Long userID, @Valid @RequestBody AddressDTO addressDTO) {
        userService.updateAddress(userID, addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    @PutMapping("/profil/{id}/settings")
    @PreAuthorize("#userID == authentication.principal.userId")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long userID, @Valid @RequestBody PasswordDTO passwordDTO) {
        userService.updatePassword(userID, passwordDTO);
        return ResponseEntity.ok().build();
    }
}
