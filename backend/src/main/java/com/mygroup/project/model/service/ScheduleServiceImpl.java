package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.ScheduleDTO;
import com.mygroup.project.model.entity.Schedule;
import com.mygroup.project.model.repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleServiceImpl implements IService<ScheduleDTO> {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ScheduleDTO get(Long id) {
        return modelMapper.map(scheduleRepository.findById(id).orElseThrow(DataNotFoundException::new), ScheduleDTO.class);
    }

    @Override
    public Collection<ScheduleDTO> getAll() {
        return modelMapper.map(scheduleRepository.findAll(), new TypeToken<Set<ScheduleDTO>>(){}.getType());
    }

    @Override
    public ScheduleDTO create(ScheduleDTO scheduleDTO) {
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        scheduleRepository.save(schedule);
        return scheduleDTO;
    }

    @Override
    public ScheduleDTO update(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(scheduleDTO.getScheduleId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(scheduleDTO, Schedule.class);
        scheduleRepository.save(schedule);
        return scheduleDTO;
    }

    @Override
    public void delete(ScheduleDTO scheduleDTO) {
        scheduleRepository.deleteById(scheduleDTO.getScheduleId());
    }

}
