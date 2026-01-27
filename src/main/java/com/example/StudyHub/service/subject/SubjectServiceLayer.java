package com.example.StudyHub.service.subject;

import java.util.List;

import com.example.StudyHub.entity.Subject;

public interface SubjectServiceLayer {



    Subject createSubject(Long regulationId, Long branchId, Long semesterId, Subject subject);

    List<Subject> getSubjectsByRegulationBranchSemester(Long regulationId, Long branchId, Long semesterId);

    Subject updateSubject(Long subjectId, Subject subject);

    Subject deleteBySubjectId(Long subjectId);
}
 