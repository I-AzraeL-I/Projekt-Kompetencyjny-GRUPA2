package com.mygroup.project.model.repository;

import com.mygroup.project.model.dto.specialized.UserSubjectOfferDTO;
import com.mygroup.project.model.entity.UserSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserSubjectRepository extends JpaRepository<UserSubject, Long> {

    @Query(nativeQuery = true, value =
            "SELECT user_subject.user_subject_id AS userSubjectId, subject.subject_name AS subjectName, " +
            "users.user_id AS userId, users.first_name AS firstName, " +
            "address.city AS city, users.description AS description " +
            "FROM user_subject " +
            "JOIN subject ON subject.subject_id = user_subject.subject_subject_id " +
            "JOIN users ON users.user_id = user_subject.user_user_id " +
            "JOIN address ON address.address_id = users.address_address_id " +
            "JOIN role on role.role_id = user_subject.role_role_id " +
            "WHERE role.role_name = :prole")
    Collection<UserSubjectOfferDTO> getAllOffers(@Param("prole") String role);

    Collection<UserSubject> findAllByUser_UserIdAndRole_RoleName(Long userId, String role);

}
