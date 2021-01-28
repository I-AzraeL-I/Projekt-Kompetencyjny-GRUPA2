package com.mygroup.project.model.repository;

import com.mygroup.project.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<Tutor> findByUser_UserId(Long id);
}
