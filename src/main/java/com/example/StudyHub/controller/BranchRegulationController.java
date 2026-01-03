package com.example.StudyHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.entity.BranchRegulation;
import com.example.StudyHub.entity.Regulation;
import com.example.StudyHub.service.branch.BranchServiceLayer;
import com.example.StudyHub.service.branchRegulation.BranchRegulationServiceLayer;
import com.example.StudyHub.service.regulation.RegulationServiceLayer;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "http://localhost:3000")
public class BranchRegulationController {
    
    private BranchServiceLayer branchServiceLayer;
    private BranchRegulationServiceLayer regulationBranchServiceLayer;
    private RegulationServiceLayer regulationServiceLayer;

    public BranchRegulationController(BranchServiceLayer branchServiceLayer,
        BranchRegulationServiceLayer regulationBranchServiceLayer,
        RegulationServiceLayer regulationServiceLayer)
    {
        this.branchServiceLayer = branchServiceLayer;
        this.regulationBranchServiceLayer = regulationBranchServiceLayer;
        this.regulationServiceLayer = regulationServiceLayer;
    }

    @GetMapping("/{regulationId}")
    public List<Branch> getBranches(@PathVariable("regulationId") Long regulationId)
    {
        //Getting list of Branches from BranchRegulation from RegulationId
        List<Branch> branches =regulationBranchServiceLayer.getBranchByRegulationId(regulationId);
        return branches;
    }

    @PostMapping("/{regulationId}")
    public Branch createBranchRegulation(@PathVariable("regulationId") Long regulationId, @RequestBody Branch branch)
    {
        //Getting Regulation from Regulation Id
        Regulation regulation=regulationServiceLayer.getRegulationById(regulationId);
        
        //Checks does branch exist with branch Name
        Branch existingBranch=branchServiceLayer.getByName(branch.getName());

        //if no branch exist with branch name then it creates Branch with Branch name 
        if(existingBranch==null){
            existingBranch=branchServiceLayer.createBranch(branch);
        }

        //Checks does branchRegulation exist with branch id and regulation id 
        BranchRegulation branchRegulation=regulationBranchServiceLayer.getBranchRegulationByIdAndBranchId(regulationId, existingBranch.getId());
        
        //if no BranchRegulation then it creates BranchRegulation
        if(branchRegulation==null){
            BranchRegulation br=new BranchRegulation();
            br.setBranch(existingBranch);
            br.setRegulation(regulation);
            branchRegulation=regulationBranchServiceLayer.createBranchRegulation(br);
        }
        
        return existingBranch;
    }
}
