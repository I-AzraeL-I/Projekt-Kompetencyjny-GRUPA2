package com.mygroup.project.model.service;

import com.mygroup.project.exception.DataNotFoundException;
import com.mygroup.project.model.dto.basic.RoleDTO;
import com.mygroup.project.model.entity.Role;
import com.mygroup.project.model.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements IService<RoleDTO> {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDTO get(Long id) {
        return modelMapper.map(roleRepository.findById(id).orElseThrow(DataNotFoundException::new), RoleDTO.class);
    }

    public RoleDTO getByRoleName(String role) {
        return modelMapper.map(roleRepository.getByRoleName(role).orElseThrow(DataNotFoundException::new), RoleDTO.class);
    }

    @Override
    public Collection<RoleDTO> getAll() {
        return modelMapper.map(roleRepository.findAll(), new TypeToken<Set<RoleDTO>>(){}.getType());
    }

    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        roleRepository.save(role);
        return roleDTO;
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getRoleId()).orElseThrow(DataNotFoundException::new);
        modelMapper.map(roleDTO, role);
        roleRepository.save(role);
        return roleDTO;
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        roleRepository.deleteById(roleDTO.getRoleId());
    }
}
