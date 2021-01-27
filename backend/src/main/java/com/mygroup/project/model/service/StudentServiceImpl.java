package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.StudentDTO;
import com.mygroup.project.model.entity.Student;
import com.mygroup.project.model.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;
import java.util.Set;

public class StudentServiceImpl implements IService<StudentDTO> {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDTO get(Long id) {
        return modelMapper.map(studentRepository.findById(id).orElseThrow(DataNotFoundException::new), StudentDTO.class);
    }

    @Override
    public Collection<StudentDTO> getAll() {
        return modelMapper.map(studentRepository.findAll(), new TypeToken<Set<StudentDTO>>(){}.getType());
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        studentRepository.save(student);
        return studentDTO;
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getStudentId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(studentDTO, student);
        studentRepository.save(student);
        return studentDTO;
    }

    @Override
    public void delete(StudentDTO studentDTO) {
        studentRepository.deleteById(studentDTO.getStudentId());
    }

}
