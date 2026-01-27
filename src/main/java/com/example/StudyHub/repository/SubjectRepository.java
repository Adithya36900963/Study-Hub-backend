package com.example.StudyHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.StudyHub.entity.Subject;

import jakarta.transaction.Transactional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    @Query("SELECT s FROM Subject s WHERE s.code = :code")
    Subject findByCode(@Param("code") String code);

    @Transactional
    @Modifying
    @Query("Delete from Subject s where s in :subjects")
    void deleteBySubjects(List<Subject> subjects);

     
}
