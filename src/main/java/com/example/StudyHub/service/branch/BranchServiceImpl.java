package com.example.StudyHub.service.branch;


import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchServiceLayer {

    private BranchRepository branchRepository;
    
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch createBranch(Branch branch) {
        Branch existingBranch = branchRepository.getByName(branch.getName());
        if(existingBranch != null){
            return null;
        }
        return branchRepository.save(branch);
    }

    @Override
    public Branch getByName(String name) {
        return branchRepository.getByName(name);
    }

    @Override
    public Branch getBranchById(Long branchId) {
        return branchRepository.findById(branchId).orElse(null);
    }

}
