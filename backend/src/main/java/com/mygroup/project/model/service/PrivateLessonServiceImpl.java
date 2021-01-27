package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.PrivateLessonDTO;
import com.mygroup.project.model.dto.basic.SubjectDTO;
import com.mygroup.project.model.entity.PrivateLesson;
import com.mygroup.project.model.entity.Student;
import com.mygroup.project.model.entity.Subject;
import com.mygroup.project.model.entity.Tutor;
import com.mygroup.project.model.repository.PrivateLessonRepository;
import com.mygroup.project.model.repository.StudentRepository;
import com.mygroup.project.model.repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrivateLessonServiceImpl implements IService<PrivateLessonDTO> {

    private final PrivateLessonRepository privateLessonRepository;
    private final TutorRepository tutorRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public PrivateLessonServiceImpl(PrivateLessonRepository privateLessonRepository, TutorRepository tutorRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.privateLessonRepository = privateLessonRepository;
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
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
                .sorted(Comparator.comparing(PrivateLessonDTO::getPrivateLessonDate)
                        .thenComparing(PrivateLessonDTO::getPrivateLessonStartHour))
                .collect(Collectors.toList());
    }

    @Override
    public PrivateLessonDTO create(PrivateLessonDTO privateLessonDTO) {
        PrivateLesson privateLesson = translateFromDTO(privateLessonDTO);
        privateLessonRepository.save(privateLesson);
        return privateLessonDTO;
    }

    @Override
    public PrivateLessonDTO update(PrivateLessonDTO privateLessonDTO) {
        PrivateLesson privateLesson = translateFromDTO(privateLessonDTO);
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
                .sorted(Comparator.comparing(PrivateLessonDTO::getPrivateLessonDate)
                        .thenComparing(PrivateLessonDTO::getPrivateLessonStartHour))
                .collect(Collectors.toList());
    }

    public Collection<PrivateLessonDTO> getByTutorId(Long id) {
        return privateLessonRepository.findAllByTutor_User_UserId(id).stream()
                .map(this::translateToDTO)
                .sorted(Comparator.comparing(PrivateLessonDTO::getPrivateLessonDate)
                        .thenComparing(PrivateLessonDTO::getPrivateLessonStartHour))
                .collect(Collectors.toList());
    }

    private PrivateLessonDTO translateToDTO(PrivateLesson privateLesson) {
        PrivateLessonDTO privateLessonDTO = new PrivateLessonDTO();
        privateLessonDTO.setPrivateLessonId(privateLesson.getPrivateLessonId());
        privateLessonDTO.setPrice(privateLesson.getPrice());
        privateLessonDTO.setAcceptance(privateLesson.getAcceptance());
        privateLessonDTO.setPrivateLessonDate(privateLesson.getPrivateLessonDate());
        privateLessonDTO.setPrivateLessonStartHour(privateLesson.getPrivateLessonStartHour());
        privateLessonDTO.setPrivateLessonEndHour(privateLesson.getPrivateLessonEndHour());
        privateLessonDTO.setTutorId(privateLesson.getTutor().getTutorId());
        privateLessonDTO.setTutorFirstName(privateLesson.getTutor().getUser().getFirstName());
        privateLessonDTO.setTutorLastName(privateLesson.getTutor().getUser().getLastName());
        privateLessonDTO.setStudentId(privateLesson.getStudent().getStudentId());
        privateLessonDTO.setStudentFirstName(privateLesson.getStudent().getUser().getFirstName());
        privateLessonDTO.setStudentLastName(privateLesson.getStudent().getUser().getLastName());
        privateLessonDTO.setSubject(modelMapper.map(privateLesson.getSubject(), SubjectDTO.class));
        privateLessonDTO.setLink(privateLesson.getLink());
        return privateLessonDTO;
    }

    private PrivateLesson translateFromDTO(PrivateLessonDTO privateLessonDTO) {
        PrivateLesson privateLesson = new PrivateLesson();
        privateLesson.setPrivateLessonDate(privateLessonDTO.getPrivateLessonDate());
        privateLesson.setPrivateLessonStartHour(privateLessonDTO.getPrivateLessonStartHour());
        privateLesson.setPrivateLessonEndHour(privateLessonDTO.getPrivateLessonEndHour());
        privateLesson.setPrice(privateLessonDTO.getPrice());
        Optional<Tutor> tutor = tutorRepository.findById(privateLessonDTO.getTutorId());
        tutor.ifPresent(theTutor -> privateLesson.setTutor(tutor.get()));
        Optional<Student> student = studentRepository.findById(privateLessonDTO.getStudentId());
        student.ifPresent(theStudent -> privateLesson.setStudent(student.get()));
        privateLesson.setSubject(modelMapper.map(privateLessonDTO.getSubject(), Subject.class));
        privateLesson.setAcceptance(privateLessonDTO.getAcceptance());
        privateLesson.setLink(privateLessonDTO.getLink());
        return privateLesson;
    }

}
