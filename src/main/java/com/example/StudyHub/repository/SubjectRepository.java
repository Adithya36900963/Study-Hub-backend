package com.example.StudyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.StudyHub.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    @Query("SELECT s FROM Subject s WHERE s.name = :name")
    Subject findByName(@Param("name") String name);
    
}
