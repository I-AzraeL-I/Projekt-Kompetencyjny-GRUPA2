package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.exception.UserAlreadyExistsException;
import com.mygroup.project.model.entity.User;
import com.mygroup.project.model.dto.UserDTO;
import com.mygroup.project.model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements IService<UserDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final static String userRole = "ROLE_USER";

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO get(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(DataNotFoundException::new), UserDTO.class);
    }

    private Optional<User> getByEmail(String email) {
        return userRepository.findByContact_EmailAddress(email);
    }

    @Override
    public Collection<UserDTO> getAll() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<Set<UserDTO>>(){}.getType());
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        getByEmail(userDTO.getContact().getEmailAddress()).ifPresent(e -> {
            throw new UserAlreadyExistsException(userDTO.getContact().getEmailAddress());
        });
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        userDTO.setUserId(user.getUserId());
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(userDTO, user);
        user = userRepository.save(user);
        userDTO.setUserId(user.getUserId());
        return userDTO;
    }

    @Override
    public void delete(UserDTO userDTO) {
        userRepository.deleteById(userDTO.getUserId());
    }

}
