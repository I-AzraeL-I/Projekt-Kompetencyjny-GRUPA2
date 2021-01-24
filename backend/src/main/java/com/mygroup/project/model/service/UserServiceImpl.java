package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataAlreadyExistsException;
import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.exception.UserAlreadyExistsException;
import com.mygroup.project.model.dto.basic.AddressDTO;
import com.mygroup.project.model.dto.basic.ContactDTO;
import com.mygroup.project.model.dto.basic.UserSubjectDTO;
import com.mygroup.project.model.dto.specialized.PasswordDTO;
import com.mygroup.project.model.entity.Role;
import com.mygroup.project.model.entity.Subject;
import com.mygroup.project.model.entity.User;
import com.mygroup.project.model.dto.basic.UserDTO;
import com.mygroup.project.model.entity.UserSubject;
import com.mygroup.project.model.repository.RoleRepository;
import com.mygroup.project.model.repository.SubjectRepository;
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
    private final SubjectRepository subjectRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final static String userRole = "ROLE_USER"; //not associated with student and teacher role, used only by spring auth

    public UserServiceImpl(UserRepository userRepository, SubjectRepository subjectRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO get(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(DataNotFoundException::new), UserDTO.class);
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
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public void delete(UserDTO userDTO) {
        userRepository.deleteById(userDTO.getUserId());
    }

    private Optional<User> getByEmail(String email) {
        return userRepository.findByContact_EmailAddress(email);
    }

    public void addUserSubject(UserSubjectDTO userSubjectDTO) {
        User user = userRepository.findById(userSubjectDTO.getUserId()).orElseThrow(DataNotFoundException::new);
        if (user.getUserSubjects().stream()
                .map(e -> e.getSubject().getSubjectId())
                .anyMatch(e -> e.equals(userSubjectDTO.getSubjectId()))) {
            throw new DataAlreadyExistsException(" subject id " + userSubjectDTO.getSubjectId());
        }
        Subject subject = subjectRepository.findById(userSubjectDTO.getSubjectId()).orElseThrow(DataNotFoundException::new);
        Role role = roleRepository.findById(userSubjectDTO.getRoleId()).orElseThrow(DataNotFoundException::new);
        UserSubject userSubject = new UserSubject();
        userSubject.setRole(role);
        userSubject.setSubject(subject);
        userSubject.setUser(user);
        user.getUserSubjects().add(userSubject);
        userRepository.save(user);
    }

    public void removeUserSubject(UserSubjectDTO userSubjectDTO) {
        User user = userRepository.findById(userSubjectDTO.getUserId()).orElseThrow(DataNotFoundException::new);
        System.err.println(user.getUserSubjects().removeIf(e -> e.getSubject().getSubjectId().equals(userSubjectDTO.getSubjectId())));
        userRepository.save(user);
    }

    public void updateContact(Long userId, ContactDTO contactDTO) {
        User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
        modelMapper.map(contactDTO, user.getContact());
        userRepository.save(user);
    }

    public void updateAddress(Long userId, AddressDTO addressDTO) {
        User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
        modelMapper.map(addressDTO, user.getAddress());
        userRepository.save(user);
    }

    public void updatePassword(Long userId, PasswordDTO passwordDTO) {
        User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
        user.setPassword(passwordEncoder.encode(passwordDTO.getPlainPassword()));
        userRepository.save(user);
    }

}
