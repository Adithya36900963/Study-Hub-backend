package com.example.StudyHub.service.branchRegulation;

import java.util.List;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.entity.BranchRegulation;

public interface BranchRegulationServiceLayer {
    BranchRegulation createBranchRegulation(BranchRegulation regulationBranch);
    List<Branch> getBranchByRegulationId(Long id);
    BranchRegulation getBranchRegulationByIdAndBranchId(Long regulationId,Long branchId);
}
