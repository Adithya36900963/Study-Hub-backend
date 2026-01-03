package com.example.StudyHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.entity.SubjectRegulationBranchSemester;
import com.example.StudyHub.service.branch.BranchServiceLayer;
import com.example.StudyHub.service.regulation.RegulationServiceLayer;
import com.example.StudyHub.service.semester.SemesterServiceLayer;
import com.example.StudyHub.service.subject.SubjectServiceLayer;
import com.example.StudyHub.service.subjectRegulationBranchSemester.SubjectRegulationBranchSemesterServiceLayer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    
    private RegulationServiceLayer regulationServiceLayer;
    private BranchServiceLayer branchServiceLayer;
    private SemesterServiceLayer semesterServiceLayer;
    private SubjectServiceLayer subjectServiceLayer;
    private SubjectRegulationBranchSemesterServiceLayer srbsServiceLayer;
    
    public SubjectController(SubjectServiceLayer subjectServiceLayer,
        SubjectRegulationBranchSemesterServiceLayer srbsServiceLayer,
        RegulationServiceLayer regulationServiceLayer,
        BranchServiceLayer branchServiceLayer,
        SemesterServiceLayer semesterServiceLayer)
    {
        this.regulationServiceLayer=regulationServiceLayer;
        this.branchServiceLayer=branchServiceLayer;
        this.semesterServiceLayer=semesterServiceLayer;
        this.subjectServiceLayer=subjectServiceLayer;
        this.srbsServiceLayer=srbsServiceLayer;
    }

    //Creating subject along with srbs mapping
    @PostMapping("/{regulationId}/{branchId}/{semesterId}")
    public Subject createSubject(@PathVariable("regulationId") Long regulationId,
                                @PathVariable("branchId") Long branchId,
                                @PathVariable("semesterId") Long semesterId,
                                @RequestBody Subject subject) {
        Subject existingSubject=subjectServiceLayer.getSubjectByName(subject.getName());
        if(existingSubject==null){
            existingSubject=subjectServiceLayer.createSubject(subject);
        }
        SubjectRegulationBranchSemester srbs=srbsServiceLayer.getSRBSByIds(regulationId, branchId, semesterId,existingSubject.getId());
        
        //if null then creates srbs
        if(srbs==null){
            srbs=new SubjectRegulationBranchSemester();
            srbs.setRegulation(regulationServiceLayer.getRegulationById(regulationId));
            srbs.setBranch(branchServiceLayer.getBranchById(branchId));
            srbs.setSemester(semesterServiceLayer.getSemesterById(semesterId));
            srbs.setSubject(existingSubject);
            srbs=srbsServiceLayer.createSRBS(srbs);
        }
        
        return existingSubject;
    }

    //Getting subjects along with srbs mapping
    @GetMapping("/{regulationId}/{branchId}/{semesterId}")
    public List<Subject> getSubjects(@PathVariable("regulationId") Long regulationId,
                                @PathVariable("branchId") Long branchId,
                                @PathVariable("semesterId") Long semesterId) {
        return srbsServiceLayer.getSubjectsByRegulationBranchSemester(regulationId, branchId, semesterId);
    }
}