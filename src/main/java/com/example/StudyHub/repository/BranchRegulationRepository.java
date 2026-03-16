package com.example.StudyHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.entity.BranchRegulation;

import jakarta.transaction.Transactional;

@Repository
public interface BranchRegulationRepository extends JpaRepository<BranchRegulation, Long> {

    @Query("SELECT br.branch FROM BranchRegulation br WHERE br.regulation.id = :regulationId")
    List<Branch> findBranchesByRegulationId(@Param("regulationId") Long regulationId);

    @Query("SELECT br FROM BranchRegulation br WHERE br.regulation.id = :regulationId AND br.branch.id = :branchId")
    BranchRegulation findByRegulationIdAndBranchId(@Param("regulationId") Long regulationId,
    @Param("branchId") Long branchId);

    //Deleting BranchRegulation from Regulation Id
    @Transactional
    @Modifying 
    @Query("Delete from BranchRegulation br where br.regulation.id=:id")
    void deleteRegulationById(Long id);

    @Transactional
    @Modifying
    @Query("Delete from BranchRegulation br where br.regulation.id=:regulationId and br.branch.id=:branchId")
    void deleteByRegulationIdAndBranchId(Long branchId, Long regulationId);

    
} 