package com.example.StudyHub.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.model.ResponseModel;
import com.example.StudyHub.service.subject.SubjectServiceLayer;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/subjects") 
public class SubjectController {
    
    private SubjectServiceLayer subjectServiceLayer;
    
    public SubjectController(
    SubjectServiceLayer subjectServiceLayer)
    {
        this.subjectServiceLayer = subjectServiceLayer;
    }

    //Creating subject along with srbs mapping
    @PostMapping("/{regulationId}/{branchId}/{semesterId}")
    public ResponseEntity<Map<String,Object>> createSubject(@PathVariable("regulationId") Long regulationId,
                                @PathVariable("branchId") Long branchId,
                                @PathVariable("semesterId") Long semesterId,
                                @RequestBody Subject subject) {

        ResponseModel<Subject> res=new ResponseModel<>(201, "Created sucessflly", subjectServiceLayer.createSubject(regulationId,branchId,semesterId,subject));
        return res.res();
        
    }

    //Getting subjects along with srbs mapping
    @GetMapping("/{regulationId}/{branchId}/{semesterId}")
    public  ResponseEntity<Map<String,Object>> getSubjects(@PathVariable("regulationId") Long regulationId,
                                @PathVariable("branchId") Long branchId,
                                @PathVariable("semesterId") Long semesterId) {
           
        ResponseModel<List<Subject>> res=new ResponseModel<>(200, "Fetched sucessflly", subjectServiceLayer.getSubjectsByRegulationBranchSemester(regulationId, branchId, semesterId));
        return res.res();

       
    }

    //Update Subject
    @Transactional
    @PatchMapping("/{subjectId}")
    public ResponseEntity<Map<String,Object>> updateSubject(@PathVariable("subjectId")Long subjectId,@RequestBody Subject subject)
    {
        ResponseModel<Subject> res=new ResponseModel<>(200, "Updated sucessflly", subjectServiceLayer.updateSubject(subjectId,subject));
        return res.res();
    }

    //Delete by subject Id
    @Transactional
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Map<String,Object>> deleteSubject(@PathVariable("subjectId")Long subjectId)
    {     
        ResponseModel<Subject> res=new ResponseModel<>(200, "Deleted sucessflly", subjectServiceLayer.deleteBySubjectId(subjectId));
        return res.res();
    }
}