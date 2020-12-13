package com.mygroup.project.model.repository;

import com.mygroup.project.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getByRoleName(String role);
}
