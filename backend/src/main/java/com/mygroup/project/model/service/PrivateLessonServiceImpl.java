package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.PrivateLessonDTO;
import com.mygroup.project.model.dto.basic.SubjectDTO;
import com.mygroup.project.model.entity.*;
import com.mygroup.project.model.repository.*;
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
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public PrivateLessonServiceImpl(PrivateLessonRepository privateLessonRepository, TutorRepository tutorRepository, UserRepository userRepository, ModelMapper modelMapper, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.privateLessonRepository = privateLessonRepository;
        this.tutorRepository = tutorRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
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
        Optional<Subject> subject = subjectRepository.findById(privateLessonDTO.getSubject().getSubjectId());
        subject.ifPresent(theSubject -> privateLesson.setSubject(subject.get()));

        User user = userRepository.findById(privateLessonDTO.getStudentId()).orElseThrow(DataNotFoundException::new);
        Student student = new Student();
        student.setUser(user);
        studentRepository.save(student);
        privateLesson.setStudent(student);
        //privateLesson.setAcceptance(0);
        //privateLesson.setLink(privateLessonDTO.getLink());
        return privateLesson;
    }

}
