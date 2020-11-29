package com.mygroup.project.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mygroup.project.model.dto.*;
import com.mygroup.project.model.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class RegistrationController {

    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;

    RegistrationController(ModelMapper modelMapper, UserServiceImpl userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @JsonView(View.RegisterPOST.class)
    @PostMapping("/addUser")
    public ResponseEntity<?> registerUserAccount(@Valid @RequestBody UserRegisterFormDTO userRegisterFormDTO) {
        AddressDTO addressDTO = modelMapper.map(userRegisterFormDTO, AddressDTO.class);
        ContactDTO contactDTO = modelMapper.map(userRegisterFormDTO, ContactDTO.class);
        UserDTO userDTO = modelMapper.map(userRegisterFormDTO, UserDTO.class);
        userDTO.setContact(contactDTO);
        userDTO.setAddress(addressDTO);
        userService.create(userDTO);
        return ResponseEntity.created(URI.create("/profil/" + userDTO.getUserId())).headers(new HttpHeaders()).body(userDTO);
    }

}
