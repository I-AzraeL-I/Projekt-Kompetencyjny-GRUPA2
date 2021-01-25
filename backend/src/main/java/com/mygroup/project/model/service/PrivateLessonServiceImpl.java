package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.PrivateLessonDTO;
import com.mygroup.project.model.entity.PrivateLesson;
import com.mygroup.project.model.repository.PrivateLessonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class PrivateLessonServiceImpl implements IService<PrivateLessonDTO> {

    private final PrivateLessonRepository privateLessonRepository;
    private final ModelMapper modelMapper;

    public PrivateLessonServiceImpl(PrivateLessonRepository privateLessonRepository, ModelMapper modelMapper) {
        this.privateLessonRepository = privateLessonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PrivateLessonDTO get(Long id) {
        return modelMapper.map(privateLessonRepository.findById(id).orElseThrow(DataNotFoundException::new), PrivateLessonDTO.class);
    }

    @Override
    public Collection<PrivateLessonDTO> getAll() {
        return modelMapper.map(privateLessonRepository.findAll(), new TypeToken<Set<PrivateLessonDTO>>(){}.getType());
    }

    @Override
    public PrivateLessonDTO create(PrivateLessonDTO privateLessonDTO) {
        PrivateLesson privateLesson = modelMapper.map(privateLessonDTO, PrivateLesson.class);
        privateLessonRepository.save(privateLesson);
        return privateLessonDTO;
    }

    @Override
    public PrivateLessonDTO update(PrivateLessonDTO privateLessonDTO) {
        PrivateLesson privateLesson = privateLessonRepository.findById(privateLessonDTO.getPrivateLessonId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(privateLessonDTO, privateLesson);
        privateLessonRepository.save(privateLesson);
        return privateLessonDTO;
    }

    @Override
    public void delete(PrivateLessonDTO privateLessonDTO) {
        privateLessonRepository.deleteById(privateLessonDTO.getPrivateLessonId());
    }

    public Collection<PrivateLessonDTO> getByStudentId(Long id) {
        return privateLessonRepository.getAllLessons(id);
    }

}
