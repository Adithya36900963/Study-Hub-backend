package com.example.StudyHub.service.semester;

import java.util.List;

import com.example.StudyHub.entity.Semester;

public interface SemesterServiceLayer {

    List<Semester> findAll();
    
    Semester createSemester(Semester semester);

    Semester getSemesterById(Long semesterId);
}
