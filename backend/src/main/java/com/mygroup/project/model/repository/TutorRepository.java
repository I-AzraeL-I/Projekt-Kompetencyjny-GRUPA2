package com.mygroup.project.model.repository;

import com.mygroup.project.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
