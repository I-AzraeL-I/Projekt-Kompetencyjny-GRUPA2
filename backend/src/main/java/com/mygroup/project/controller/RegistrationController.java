package com.mygroup.project.controller;

import com.mygroup.project.model.dto.basic.AddressDTO;
import com.mygroup.project.model.dto.basic.ContactDTO;
import com.mygroup.project.model.dto.basic.UserDTO;
import com.mygroup.project.model.dto.specialized.UserRegisterFormDTO;
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

    @PostMapping("/addUser")
    public ResponseEntity<?> registerUserAccount(@Valid @RequestBody UserRegisterFormDTO userRegisterFormDTO) {
        AddressDTO addressDTO = modelMapper.map(userRegisterFormDTO, AddressDTO.class);
        ContactDTO contactDTO = modelMapper.map(userRegisterFormDTO, ContactDTO.class);
        UserDTO userDTO = modelMapper.map(userRegisterFormDTO, UserDTO.class);
        userDTO.setContact(contactDTO);
        userDTO.setAddress(addressDTO);
        userDTO = userService.create(userDTO);
        return ResponseEntity.created(URI.create("/profil/" + userDTO.getUserId())).headers(new HttpHeaders()).body(userDTO.getUserId());
    }

}
