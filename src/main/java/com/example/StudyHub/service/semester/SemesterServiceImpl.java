package com.example.StudyHub.service.semester;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Semester;
import com.example.StudyHub.repository.SemesterReposiory;

@Service
public class SemesterServiceImpl implements SemesterServiceLayer{

    private SemesterReposiory semesterReposiory;
    
    public SemesterServiceImpl(SemesterReposiory semesterReposiory)
    {
        this.semesterReposiory=semesterReposiory;
    }

    @Override
    public List<Semester> findAll() {
        return semesterReposiory.findAll();
    }

    @Override
    public Semester createSemester(Semester semester) {
        return semesterReposiory.save(semester);
    }

    @Override
    public Semester getSemesterById(Long semesterId) {
        
        return semesterReposiory.findById(semesterId).orElse(null);
    }

    
    
}
