package com.mygroup.project.model.repository;

import com.mygroup.project.model.dto.basic.PrivateLessonDTO;
import com.mygroup.project.model.dto.specialized.UserSubjectOfferDTO;
import com.mygroup.project.model.entity.PrivateLesson;
import com.mygroup.project.model.entity.Student;
import com.mygroup.project.model.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface PrivateLessonRepository extends JpaRepository<PrivateLesson, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM private_lesson " +
                    "JOIN tutors ON tutors.tutor_id = private_lesson.tutor_tutor_id " +
                    "JOIN students ON students.student_id = private_lesson.student_student_id " +
                    "WHERE private_lesson.student_user_user_id = :id")
    Collection<PrivateLessonDTO> getAllLessons(@Param("id") Long id);

}
