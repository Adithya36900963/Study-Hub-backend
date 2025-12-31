package com.example.StudyHub.service.subjectRegulationBranchSemester;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.entity.SubjectRegulationBranchSemester;
import com.example.StudyHub.repository.SubjectRegulationBranchSemesterRepository;

@Service
public class SubjectRegulationBranchSemesterServiceImpl implements SubjectRegulationBranchSemesterServiceLayer {

    SubjectRegulationBranchSemesterRepository srbsRepository;
    public SubjectRegulationBranchSemesterServiceImpl(SubjectRegulationBranchSemesterRepository srbsRepository)
    {
        this.srbsRepository=srbsRepository;
    }

    //get SRBS by regulationId, branchId, semesterId, subjectId
    @Override
    public SubjectRegulationBranchSemester getSRBSByIds(Long regulationId, Long branchId, Long semesterId,
            Long subjectId) {
        
     return srbsRepository.findByRegulationIdAndBranchIdAndSemesterIdAndSubjectId(regulationId, branchId, semesterId, subjectId);
    }

    //get Subjects by regulationId, branchId, semesterId
    @Override
    public List<Subject> getSubjectsByRegulationBranchSemester(Long regulationId, Long branchId, Long semesterId) {
        return srbsRepository.findSubjectsByRegulationBranchSemester(regulationId, branchId, semesterId);
    }

    //Creation of srbs
    @Override
    public SubjectRegulationBranchSemester createSRBS(SubjectRegulationBranchSemester srbs) {
        return srbsRepository.save(srbs);
    }
    
}
