package com.mygroup.project.model.repository;

import com.mygroup.project.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Collection<Tutor> findAllByUser_UserId(Long id);
}
