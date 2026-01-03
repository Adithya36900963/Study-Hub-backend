package com.example.StudyHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.service.semester.SemesterServiceLayer;
import com.example.StudyHub.entity.Semester;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/semesters")
public class SemesterController {
    private SemesterServiceLayer semesterServiceLayer;

    public SemesterController(SemesterServiceLayer semesterServiceLayer)
    {
        this.semesterServiceLayer=semesterServiceLayer;
    }

    @GetMapping
    public List<Semester> getAll()
    {
        return semesterServiceLayer.findAll();
    }

    @PostMapping
    public Semester creaSemester(@RequestBody Semester semester)
    {
        return semesterServiceLayer.createSemester(semester);
    }
}
