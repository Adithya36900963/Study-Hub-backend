package com.example.StudyHub.service.branch;

import java.util.List;

import com.example.StudyHub.entity.Branch;

public interface BranchServiceLayer {

    //Get Branch By Branch Id 
    Branch getBranchById(Long branchId);

    //Get Branch by Branch code
    Branch getByCode(String code);
    
    //Creation of Branch with Regulagtion Id
    Branch createBranchByRegulationId(Long regulationId, Branch branch);

    List<Branch> getBranchByRegulationId(Long regulationId);

    Branch updateBranch(Long branchId, Branch branch);

    // Branch deleteBranch(Long branchId);

    Branch deleteBranch(Long branchId, Long regulationId); 
} 
