package com.example.StudyHub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.service.semester.SemesterServiceLayer;

import com.example.StudyHub.entity.Semester;
import com.example.StudyHub.model.ResponseModel;

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
    public ResponseEntity<Map<String,Object>> getAll()
    {
        ResponseModel<List<Semester>> res=new ResponseModel<>(201, "Created sucessfully",semesterServiceLayer.findAll());
        return res.res();
       
    }

    @PostMapping
    public Semester createSemester(@RequestBody Semester semester)
    {
        return semesterServiceLayer.createSemester(semester);
    }
}
