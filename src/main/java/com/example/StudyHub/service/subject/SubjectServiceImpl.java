package com.example.StudyHub.service.subject;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectServiceLayer {
    
    private SubjectRepository subjectRepository;
    public SubjectServiceImpl(SubjectRepository subjectRepository)
    {
        this.subjectRepository=subjectRepository;
    }

    //Creation of Subject
    public Subject createSubject(Subject subject)
    {
        return subjectRepository.saveAndFlush(subject);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }
}
