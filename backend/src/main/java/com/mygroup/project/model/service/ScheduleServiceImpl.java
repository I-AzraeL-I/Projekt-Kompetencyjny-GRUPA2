package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.ScheduleDTO;
import com.mygroup.project.model.entity.Schedule;
import com.mygroup.project.model.repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return translateToDTO(schedule);
    }

    @Override
    public Collection<ScheduleDTO> getAll() {
        return scheduleRepository.findAll().stream()
                .map(this::translateToDTO)
                .collect(Collectors.toSet());
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

    private ScheduleDTO translateToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setScheduleId(schedule.getScheduleId());
        scheduleDTO.setDays(schedule.getDays());
        scheduleDTO.setStart(schedule.getStart());
        scheduleDTO.setEnd(schedule.getEnd());
        return scheduleDTO;
    }

}
