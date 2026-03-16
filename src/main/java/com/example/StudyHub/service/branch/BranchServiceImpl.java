package com.example.StudyHub.service.branch;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.entity.BranchRegulation;
import com.example.StudyHub.entity.Regulation;
import com.example.StudyHub.entity.Subject;


import com.example.StudyHub.repository.BranchRegulationRepository;
import com.example.StudyHub.repository.BranchRepository;
import com.example.StudyHub.repository.PDFSSubjectRepository;
import com.example.StudyHub.repository.RegulationRepository;
import com.example.StudyHub.repository.SubjectRegulationBranchSemesterRepository;
import com.example.StudyHub.repository.SubjectRepository;
 
import jakarta.transaction.Transactional;

@Service
public class BranchServiceImpl implements BranchServiceLayer {

    //Repository
    private BranchRepository br;
    private BranchRegulationRepository brr;
    private RegulationRepository rr;
    private SubjectRepository sr;
    private PDFSSubjectRepository pdfssr; 
    private SubjectRegulationBranchSemesterRepository srbsr;   

    public BranchServiceImpl(BranchRepository br,
        BranchRegulationRepository brr,
        RegulationRepository rr,
        SubjectRepository sr,
        PDFSSubjectRepository pdfssr,
        SubjectRegulationBranchSemesterRepository srbsr
) 
    {
        this.br = br;
        this.brr=brr;
        this.rr=rr;
        this.sr=sr;
        this.pdfssr=pdfssr;
        this.srbsr=srbsr;

    }


    //Get Branch By Branch Id
    @Override
    public Branch getBranchById(Long branchId) {
        return br.findById(branchId).orElse(null);
    }

    //get Branch by Branch Code
    @Override
    public Branch getByCode(String code) {
        return br.findByCode(code);
    } 

    @Override
    public List<Branch> getBranchByRegulationId(Long regulationId) {
        Regulation r=rr.findById(regulationId).orElseThrow(()->new RuntimeException("Regulation doesnt on regulation Id: "+regulationId));
        List<Branch> branches =brr.findBranchesByRegulationId(regulationId);
        if(branches.isEmpty())
        {
            return List.of(); 
        }
        return branches;
    }
 
    
    //Create Branch 
    @Transactional
    @Override
    public Branch createBranchByRegulationId(Long regulationId, Branch branch) {
        //Checks first Does Regulation exists with Regulation Id
        Regulation existingRegulation=rr.findById(regulationId).orElseThrow(()->
        new RuntimeException("Regulation Dosen't exist on this Regulation Id: "+regulationId));
        
        if(branch.getName()==null && branch.getCode()==null)
            throw new RuntimeException("Branch Name and Branch Code isn't given");

        if(branch.getName()==null)
            throw new RuntimeException("Branch Name isn't given");

        if(branch.getCode()==null)
            throw new RuntimeException("Branch Code isn't given");


        //gets Branch if Exist on Branch code
        Branch existingBranch=getByCode(branch.getCode());

        //Checks does branch exist
        if(existingBranch!=null)
        {
            //Gets  BranchRegulation if exist on branch Code and regulation Id
            BranchRegulation existingBranchRegulation=brr.findByRegulationIdAndBranchId(regulationId,existingBranch.getId());

            //Checks does branchRegulation exist
            if(existingBranchRegulation!=null)
            {
                throw new RuntimeException("BranchRegulation exist's on Branch Code:"
                +branch.getCode()+" and Regulation Id: "+regulationId);
            }

            //If no branchRegulation exist then add mapping to Branch Regulation
            brr.save(BranchRegulation.builder()
            .branch(existingBranch)
            .regulation(existingRegulation)
            .build());
            
            return existingBranch;
        }

        //Now Saving Branch in Branch in branchRepository
        Branch newBranch=br.save(branch);
        
        //Save the maping
        brr.save(BranchRegulation.builder()
        .branch(newBranch)
        .regulation(existingRegulation)
        .build());

        return newBranch;
    }


    @Transactional
    @Override
    public Branch updateBranch(Long branchId, Branch branch) {
        Branch b=getBranchById(branchId);
        if(b==null)
        {
            throw new RuntimeException("Branch doesn't exist on Branch Id: "+branchId);
        }
        
        if(branch.getName()==null && branch.getCode()==null)
            throw new RuntimeException("Branch Code and Branch Name isn't given");

        if(branch.getName()!=null)
        {
            b.setName(branch.getName());
        }
        if(branch.getCode()!=null)
            b.setCode(branch.getCode());
        return b;
    }


    @Override
    public Branch deleteBranch(Long branchId,Long regulationId) {
        Regulation r=rr.findById(regulationId).orElseThrow(()->new RuntimeException("Regulation doesnt on regulation Id: "+regulationId));
        
        Branch branch=br.findById(branchId).orElseThrow(()->new RuntimeException("Branch doesnt exsit on Branch Id: "+branchId));

        List<Subject> subjects=srbsr.findByRegulationIdAndBranchId(branchId,regulationId);
        if(subjects!=null)
        {
            pdfssr.deleteBySubjects(subjects);
            sr.deleteBySubjects(subjects);
        }
        brr.deleteByRegulationIdAndBranchId(branchId,regulationId);
        srbsr.deleteByRegulationIdAndBranchId(branchId,regulationId);
        
        return branch;
    }


 
}
