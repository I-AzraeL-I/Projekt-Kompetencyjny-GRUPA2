package com.mygroup.project.model.repository;

import com.mygroup.project.model.entity.PrivateLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PrivateLessonRepository extends JpaRepository<PrivateLesson, Long> {

    Collection<PrivateLesson> findAllByStudent_User_UserId(Long id);

    Collection<PrivateLesson> findAllByTutor_User_UserId(Long id);

    Collection<PrivateLesson> findAllByTutor_TutorId(Long id);

}
