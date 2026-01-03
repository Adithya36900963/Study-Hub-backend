package com.example.StudyHub.service.subject;

import java.util.Optional;

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

    @Override
    public Subject getById(Long id) {
        // TODO Auto-generated method stub
        return subjectRepository.findById(id).orElse(null);
        
    }
}
