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

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.model.ResponseModel;
import com.example.StudyHub.service.branch.BranchServiceLayer;
 
@RestController
@RequestMapping("/api/branches")
public class BranchController {
     
    
    private BranchServiceLayer bsl;


    //Bean Configuration using Constructor
    public BranchController(BranchServiceLayer bsl)
    {
        this.bsl = bsl;      
    }

   
    //Getting list of Branch using Regulation Id from 
    @GetMapping("/{regulationId}")
    public ResponseEntity<Map<String,Object>> getBranches(@PathVariable("regulationId") Long regulationId)
    {
        ResponseModel<List<Branch>> res=new ResponseModel<>(200, "Fetched sucessflly",bsl.getBranchByRegulationId(regulationId));
        return res.res();
    }

    //Posting Branch which map to BranchRegulation
    @PostMapping("/{regulationId}")
    public ResponseEntity<Map<String,Object>> createBranchRegulation(@PathVariable("regulationId") Long regulationId,
        @RequestBody Branch branch)
    {
        ResponseModel<Branch> res=new ResponseModel<>(201, "Created sucessflly",bsl.createBranchByRegulationId(regulationId,branch));
        return res.res();
    }

    @PatchMapping("/{branchId}")
    public ResponseEntity<Map<String,Object>> updateBranch(@PathVariable("branchId") Long branchId,@RequestBody Branch branch)
    {
        ResponseModel<Branch> res=new ResponseModel<>(200, "Updated sucessflly",bsl.updateBranch(branchId,branch));
        return res.res();
    }

    @DeleteMapping("/{regulationId}/{branchId}")
    public ResponseEntity<Map<String,Object>> deleteBranchRegulation(@PathVariable("regulationId") Long regulationId,@PathVariable("branchId") Long branchId)
    {
        ResponseModel<Branch> res=new ResponseModel<>(200, "Deleted sucessflly", bsl.deleteBranch(branchId,regulationId));
        return res.res();
        
    }
}
