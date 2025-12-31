package com.example.StudyHub.service.branch;

import com.example.StudyHub.entity.Branch;

public interface BranchServiceLayer {
    Branch createBranch(Branch branch);
    Branch getByName(String name);
    Branch getBranchById(Long branchId);
}
