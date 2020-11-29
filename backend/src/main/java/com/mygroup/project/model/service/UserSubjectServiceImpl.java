package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.UserSubjectDTO;
import com.mygroup.project.model.dto.UserSubjectOfferDTO;
import com.mygroup.project.model.entity.UserSubject;
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

    @Override
    public void create(UserSubjectDTO userSubjectDTO) {
        userSubjectRepository.save(modelMapper.map(userSubjectDTO, UserSubject.class));
    }

    @Override
    public void update(UserSubjectDTO userSubjectDTO) {
    }

    @Override
    public void delete(UserSubjectDTO userSubjectDTO) {
        userSubjectRepository.deleteById(userSubjectDTO.getUserSubjectId());
    }

    public Collection<UserSubjectOfferDTO> getAllOffersByRole(String role) {
        return userSubjectRepository.getAllOffers(role);
    }
}
