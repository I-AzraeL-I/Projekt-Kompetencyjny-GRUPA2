package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.PrivateLessonDTO;
import com.mygroup.project.model.dto.basic.SubjectDTO;
import com.mygroup.project.model.entity.PrivateLesson;
import com.mygroup.project.model.repository.PrivateLessonRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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
        PrivateLesson privateLesson = privateLessonRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return translateToDTO(privateLesson);
    }

    @Override
    public Collection<PrivateLessonDTO> getAll() {
        return privateLessonRepository.findAll().stream()
                .map(this::translateToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public PrivateLessonDTO create(PrivateLessonDTO privateLessonDTO) {
        //todo
        PrivateLesson privateLesson = modelMapper.map(privateLessonDTO, PrivateLesson.class);
        privateLessonRepository.save(privateLesson);
        return privateLessonDTO;
    }

    @Override
    public PrivateLessonDTO update(PrivateLessonDTO privateLessonDTO) {
        //todo
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
        return privateLessonRepository.findAllByStudent_User_UserId(id).stream()
                .map(this::translateToDTO)
                .collect(Collectors.toSet());
    }

    private PrivateLessonDTO translateToDTO(PrivateLesson privateLesson) {
        PrivateLessonDTO privateLessonDTO = new PrivateLessonDTO();
        privateLessonDTO.setPrivateLessonId(privateLesson.getPrivateLessonId());
        privateLessonDTO.setPrice(privateLesson.getPrice());
        privateLessonDTO.setAcceptance(privateLesson.getAcceptance());
        privateLessonDTO.setPrivateLessonDate(privateLesson.getPrivateLessonDate());
        privateLessonDTO.setPrivateLessonStartHour(privateLesson.getPrivateLessonStartHour());
        privateLessonDTO.setPrivateLessonEndHour(privateLesson.getPrivateLessonEndHour());
        privateLessonDTO.setTutorId(privateLesson.getTutor().getUser().getUserId());
        privateLessonDTO.setTutorFirstName(privateLesson.getTutor().getUser().getFirstName());
        privateLessonDTO.setTutorLastName(privateLesson.getTutor().getUser().getLastName());
        privateLessonDTO.setStudentId(privateLesson.getStudent().getUser().getUserId());
        privateLessonDTO.setStudentFirstName(privateLesson.getStudent().getUser().getFirstName());
        privateLessonDTO.setStudentLastName(privateLesson.getStudent().getUser().getLastName());
        privateLessonDTO.setSubject(modelMapper.map(privateLesson.getSubject(), SubjectDTO.class));
        return privateLessonDTO;
    }

}
