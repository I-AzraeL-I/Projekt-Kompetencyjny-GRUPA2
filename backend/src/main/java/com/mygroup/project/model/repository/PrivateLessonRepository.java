package com.mygroup.project.model.repository;

import com.mygroup.project.model.entity.PrivateLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateLessonRepository extends JpaRepository<PrivateLesson, Long> {
}
