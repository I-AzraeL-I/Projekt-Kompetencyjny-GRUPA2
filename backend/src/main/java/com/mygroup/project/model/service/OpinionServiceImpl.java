package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.OpinionDTO;
import com.mygroup.project.model.entity.Opinion;
import com.mygroup.project.model.entity.Tutor;
import com.mygroup.project.model.repository.OpinionRepository;
import com.mygroup.project.model.repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class OpinionServiceImpl implements IService<OpinionDTO> {

    private final OpinionRepository opinionRepository;
    private final TutorRepository tutorRepository;
    private final ModelMapper modelMapper;

    public OpinionServiceImpl(OpinionRepository opinionRepository, TutorRepository tutorRepository, ModelMapper modelMapper) {
        this.opinionRepository = opinionRepository;
        this.tutorRepository = tutorRepository;
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
        Opinion opinion = translateFromDTO(opinionDTO);
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

    private Opinion translateFromDTO(OpinionDTO opinionDTO) {
        Opinion opinion = new Opinion();
        opinion.setComment(opinionDTO.getComment());
        opinion.setRating(opinionDTO.getRating());
        Optional<Tutor> tutor = tutorRepository.findById(opinionDTO.getTutorId());
        tutor.ifPresent(theTutor -> opinion.setTutor(tutor.get()));
        return opinion;
    }

}
