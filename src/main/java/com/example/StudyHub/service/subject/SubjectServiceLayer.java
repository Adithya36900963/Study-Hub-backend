package com.example.StudyHub.service.subject;

import com.example.StudyHub.entity.Subject;

public interface SubjectServiceLayer {
    Subject createSubject(Subject subject);

    Subject getSubjectByName(String name);

    Subject getById(Long id);
}
