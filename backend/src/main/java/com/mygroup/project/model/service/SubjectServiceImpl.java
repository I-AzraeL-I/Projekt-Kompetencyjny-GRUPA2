package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.SubjectDTO;
import com.mygroup.project.model.entity.Subject;
import com.mygroup.project.model.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class SubjectServiceImpl implements IService<SubjectDTO> {

    private final ModelMapper modelMapper;
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(ModelMapper modelMapper, SubjectRepository subjectRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDTO get(Long id) {
        return modelMapper.map(subjectRepository.findById(id).orElseThrow(DataNotFoundException::new), SubjectDTO.class);
    }

    @Override
    public Collection<SubjectDTO> getAll() {
        return modelMapper.map(subjectRepository.findAll(), new TypeToken<Set<SubjectDTO>>(){}.getType());
    }

    @Override
    public SubjectDTO create(SubjectDTO subjectDTO) {
        subjectRepository.save(modelMapper.map(subjectDTO, Subject.class));
        return subjectDTO;
    }

    @Override
    public SubjectDTO update(SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.findById(subjectDTO.getSubjectId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(subjectDTO, subject);
        subjectRepository.save(subject);
        return subjectDTO;
    }

    @Override
    public void delete(SubjectDTO subjectDTO) {
        subjectRepository.deleteById(subjectDTO.getSubjectId());
    }
}
