package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.TutorDTO;
import com.mygroup.project.model.dto.basic.UserDTO;
import com.mygroup.project.model.entity.Tutor;
import com.mygroup.project.model.repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class TutorServiceImpl implements IService<TutorDTO> {

    private final TutorRepository tutorRepository;
    private final ModelMapper modelMapper;

    public TutorServiceImpl(TutorRepository tutorRepository, ModelMapper modelMapper) {
        this.tutorRepository = tutorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TutorDTO get(Long id) {
        return modelMapper.map(tutorRepository.findById(id).orElseThrow(DataNotFoundException::new), TutorDTO.class);
    }

    @Override
    public Collection<TutorDTO> getAll() {
        return modelMapper.map(tutorRepository.findAll(), new TypeToken<Set<TutorDTO>>(){}.getType());
    }

    @Override
    public TutorDTO create(TutorDTO tutorDTO) {
        Tutor tutor = modelMapper.map(tutorDTO, Tutor.class);
        tutorRepository.save(tutor);
        return tutorDTO;
    }

    @Override
    public TutorDTO update(TutorDTO tutorDTO) {
        Tutor tutor = tutorRepository.findById(tutorDTO.getTutorId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(tutorDTO, tutor);
        tutorRepository.save(tutor);
        return tutorDTO;
    }

    @Override
    public void delete(TutorDTO tutorDTO) {
        tutorRepository.deleteById(tutorDTO.getTutorId());
    }

    public Collection<TutorDTO> getAllByUserId(Long id) {
        return modelMapper.map(tutorRepository.findAllByUser_UserId(id), new TypeToken<Set<TutorDTO>>(){}.getType());
    }

}
