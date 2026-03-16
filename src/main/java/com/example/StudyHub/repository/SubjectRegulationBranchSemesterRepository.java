package com.example.StudyHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.entity.SubjectRegulationBranchSemester;

import jakarta.transaction.Transactional;

@Repository
public interface SubjectRegulationBranchSemesterRepository extends JpaRepository<SubjectRegulationBranchSemester, Long> {
    
    @Query("SELECT s FROM SubjectRegulationBranchSemester s WHERE s.regulation.id = :regulationId AND s.branch.id = :branchId AND s.semester.id = :semesterId AND s.subject.id = :subjectId")
    SubjectRegulationBranchSemester findByRegulationIdAndBranchIdAndSemesterIdAndSubjectId
    (@Param("regulationId") Long regulationId,@Param("branchId") Long branchId, @Param("semesterId") Long semesterId, @Param("subjectId") Long subjectId);

    @Query("SELECT s.subject FROM SubjectRegulationBranchSemester s WHERE s.regulation.id = :regulationId AND s.branch.id = :branchId AND s.semester.id = :semesterId")
    List<Subject> findSubjectsByRegulationBranchSemester(@Param("regulationId") Long regulationId, @Param("branchId") Long branchId, @Param("semesterId") Long semesterId);

   

    @Query("Select srbs.regulation from SubjectRegulationBranchSemester srbs where srbs.regulation.id=:id")
    Object findByRegulationId(@Param("id") Long id);

    //Delete SubjectRegulationBranchSemester by Regulation Id
    @Transactional
    @Modifying
    @Query("Delete from SubjectRegulationBranchSemester srbs where srbs.regulation.id=:id")
    void deleteByRegulationId(@Param("id") Long id);

    @Query("Select srbs.subject from SubjectRegulationBranchSemester  srbs where srbs.regulation.id=:id")
    List<Subject> findSubjectByRegulationId(@Param("id") Long id);


    @Query("Select srbs.subject from SubjectRegulationBranchSemester  srbs where srbs.branch.id=:id")
    List<Subject> findSubjectByBranchId(@Param("id") Long id);

    @Query("Select srbs.subject from SubjectRegulationBranchSemester  srbs where srbs.branch.id=:branchId and srbs.branch.id=:regulationId")
    List<Subject> findByRegulationIdAndBranchId(@Param("regulationId") Long regulationId,@Param("branchId") Long branchId);

    @Transactional
    @Modifying
    @Query("Delete from SubjectRegulationBranchSemester srbs where srbs.regulation.id=:regulationId and srbs.branch.id=:branchId")
    void deleteByRegulationIdAndBranchId(@Param("regulationId") Long regulationId,@Param("branchId") Long branchId);


    @Modifying
    @Query("Delete from SubjectRegulationBranchSemester srbs where srbs.subject.id=:subjectId")
    void deleteBySubjectId(@Param("subjectId")Long subjectId);

    
} 
