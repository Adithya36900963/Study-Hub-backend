package com.example.StudyHub.service.subjectRegulationBranchSemester;

import java.util.List;
import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.entity.SubjectRegulationBranchSemester;

public interface SubjectRegulationBranchSemesterServiceLayer {
    SubjectRegulationBranchSemester getSRBSByIds(Long regulationId, Long branchId, Long semesterId,Long subjectId);

    List<Subject> getSubjectsByRegulationBranchSemester(Long regulationId, Long branchId, Long semesterId);

    SubjectRegulationBranchSemester createSRBS(SubjectRegulationBranchSemester srbs);
}
