package com.example.StudyHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.entity.SubjectRegulationBranchSemester;

@Repository
public interface SubjectRegulationBranchSemesterRepository extends JpaRepository<SubjectRegulationBranchSemester, Long> {
    
    @Query("SELECT s FROM SubjectRegulationBranchSemester s WHERE s.regulation.id = :regulationId AND s.branch.id = :branchId AND s.semester.id = :semesterId AND s.subject.id = :subjectId")
    SubjectRegulationBranchSemester findByRegulationIdAndBranchIdAndSemesterIdAndSubjectId
    (@Param("regulationId") Long regulationId,@Param("branchId") Long branchId, @Param("semesterId") Long semesterId, @Param("subjectId") Long subjectId);

    @Query("SELECT s.subject FROM SubjectRegulationBranchSemester s WHERE s.regulation.id = :regulationId AND s.branch.id = :branchId AND s.semester.id = :semesterId")
    List<Subject> findSubjectsByRegulationBranchSemester(@Param("regulationId") Long regulationId, @Param("branchId") Long branchId, @Param("semesterId") Long semesterId);
}
