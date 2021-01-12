package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.OpinionDTO;
import com.mygroup.project.model.entity.Opinion;
import com.mygroup.project.model.repository.OpinionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class OpinionServiceImpl implements IService<OpinionDTO> {

    private final OpinionRepository opinionRepository;
    private final ModelMapper modelMapper;

    public OpinionServiceImpl(OpinionRepository opinionRepository, ModelMapper modelMapper) {
        this.opinionRepository = opinionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OpinionDTO get(Long id) {
        return modelMapper.map(opinionRepository.findById(id).orElseThrow(DataNotFoundException::new), OpinionDTO.class);
    }

    @Override
    public Collection<OpinionDTO> getAll() {
        return modelMapper.map(opinionRepository.findAll(), new TypeToken<Set<OpinionDTO>>(){}.getType());
    }

    @Override
    public OpinionDTO create(OpinionDTO opinionDTO) {
        Opinion opinion = modelMapper.map(opinionDTO, Opinion.class);
        opinionRepository.save(opinion);
        return opinionDTO;
    }

    @Override
    public OpinionDTO update(OpinionDTO opinionDTO) {
        Opinion opinion = opinionRepository.findById(opinionDTO.getOpinionId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(opinionDTO, opinion);
        opinionRepository.save(opinion);
        return opinionDTO;
    }

    @Override
    public void delete(OpinionDTO opinionDTO) {
        opinionRepository.deleteById(opinionDTO.getOpinionId());
    }

}
