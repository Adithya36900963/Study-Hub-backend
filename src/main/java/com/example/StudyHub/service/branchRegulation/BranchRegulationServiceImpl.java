package com.example.StudyHub.service.branchRegulation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.entity.BranchRegulation;
import com.example.StudyHub.repository.BranchRegulationRepository;

@Service
public class BranchRegulationServiceImpl implements BranchRegulationServiceLayer {

    private BranchRegulationRepository branchRegulationRepository;

    //Bean configuration using Constructor
    public BranchRegulationServiceImpl(BranchRegulationRepository branchRegulationRepository) {
        this.branchRegulationRepository = branchRegulationRepository;
    }

    @Override
    public BranchRegulation createBranchRegulation(BranchRegulation regulationBranch) {
        return branchRegulationRepository.save(regulationBranch);
    }

    @Override
    public List<Branch> getBranchByRegulationId(Long id) {
        return branchRegulationRepository.findBranchesByRegulationId(id);

    }
    @Override
    public BranchRegulation getBranchRegulationByIdAndBranchId(Long regulationId,Long branchId) {

        return branchRegulationRepository.findByRegulationIdAndBranchId(regulationId, branchId);
    }
}
