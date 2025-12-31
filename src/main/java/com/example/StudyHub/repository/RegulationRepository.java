package com.example.StudyHub.repository;

import org.springframework.stereotype.Repository;
import com.example.StudyHub.entity.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation,Long> {
    @Query("SELECT r FROM Regulation r WHERE r.name = :name")
    Regulation findByName(@Param("name")String name);
}
