package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.UserSubjectDTO;
import com.mygroup.project.model.dto.specialized.UserSubjectOfferDTO;
import com.mygroup.project.model.repository.UserSubjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class UserSubjectServiceImpl implements IService<UserSubjectDTO> {

    private final UserSubjectRepository userSubjectRepository;
    private final ModelMapper modelMapper;

    public UserSubjectServiceImpl(UserSubjectRepository userSubjectRepository, ModelMapper modelMapper) {
        this.userSubjectRepository = userSubjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserSubjectDTO get(Long id) {
        return modelMapper.map(userSubjectRepository.findById(id).orElseThrow(DataNotFoundException::new), UserSubjectDTO.class);
    }

    @Override
    public Collection<UserSubjectDTO> getAll() {
        return modelMapper.map(userSubjectRepository.findAll(), new TypeToken<Set<UserSubjectDTO>>(){}.getType());
    }

    public Collection<UserSubjectDTO> getAllByIdAndRole(Long id, String role) {
        return modelMapper.map(userSubjectRepository.findAllByUser_UserIdAndRole_RoleName(id, role), new TypeToken<Set<UserSubjectDTO>>(){}.getType());
    }

    public Collection<UserSubjectOfferDTO> getAllByRole(String role) {
        return userSubjectRepository.getAllOffers(role);
    }

    //do not use directly
    @Override
    public UserSubjectDTO create(UserSubjectDTO userSubjectDTO) {
        return null;
    }

    //do not use directly
    @Override
    public UserSubjectDTO update(UserSubjectDTO userSubjectDTO) {
        return null;
    }

    //do not use directly
    @Override
    public void delete(UserSubjectDTO userSubjectDTO) {
    }
}
