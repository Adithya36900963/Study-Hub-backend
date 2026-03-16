package com.example.StudyHub.service.subject;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Branch;
import com.example.StudyHub.entity.Regulation;
import com.example.StudyHub.entity.Semester;
import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.entity.SubjectRegulationBranchSemester;
import com.example.StudyHub.repository.BranchRepository;
import com.example.StudyHub.repository.PDFSSubjectRepository;
import com.example.StudyHub.repository.RegulationRepository;
import com.example.StudyHub.repository.SemesterReposiory;
import com.example.StudyHub.repository.SubjectRegulationBranchSemesterRepository;
import com.example.StudyHub.repository.SubjectRepository;

import jakarta.transaction.Transactional;

@Service
public class SubjectServiceImpl implements SubjectServiceLayer {
    
    private RegulationRepository rr;
    private BranchRepository br;
    private SemesterReposiory sr;
    private SubjectRepository subjectRepository;
    private SubjectRegulationBranchSemesterRepository srbsr;
    private PDFSSubjectRepository pdfssr;
   
    
    public SubjectServiceImpl(
        RegulationRepository rr,
        BranchRepository br,
        SemesterReposiory sr,
        SubjectRepository subjectRepository,
        SubjectRegulationBranchSemesterRepository srbsr,
        PDFSSubjectRepository pdfssr
    )
    { 
        this.rr=rr;
        this.br=br;
        this.sr=sr;
        this.subjectRepository=subjectRepository;
        this.srbsr=srbsr;
        this.pdfssr=pdfssr;
    } 

    
    //Creation of Subject where mapping is done to
    @Transactional
    public Subject createSubject(Long regulationId,Long branchId,Long semesterId,Subject subject)
    {
        //Checking does regulation exist in regulation Id
        Regulation existingRegulation=rr.findById(regulationId).orElseThrow(()->
        new RuntimeException("Regulation deosn't exist on Regulation Id: "+regulationId));

        //Checking does Branch exist in Branch Id
        Branch existingBranch=br.findById(branchId).orElseThrow(()->
        new RuntimeException("Branch deosn't exist on Branch Id: "+branchId));


         //Checking does Semester exist in Semester Id
        Semester existingSemester=sr.findById(semesterId).orElseThrow(()->
        new RuntimeException("Semester deosn't exist on Semester Id: "+semesterId));

        if(subject.getName()==null && subject.getCode()==null)
            throw new RuntimeException("Subject Name and Subject Code isn't given");

        if(subject.getName()==null )
            throw new RuntimeException("Subject Name isn't given");

        if(subject.getCode()==null)
            throw new RuntimeException("Subject Code isn't given");

        Subject existingSubject=subjectRepository.findByCode(subject.getCode());
        
        if(existingSubject==null)
            existingSubject=subjectRepository.save(subject);

        
        
        SubjectRegulationBranchSemester srbs=srbsr.
        findByRegulationIdAndBranchIdAndSemesterIdAndSubjectId(regulationId, branchId, semesterId,existingSubject.getId());
        
        if(srbs!=null)
        {
            throw new RuntimeException("Subject alredy exist's on this Regulation,Branch,Semester");
        }
        //if null then creates srbs
       
        srbs=SubjectRegulationBranchSemester
        .builder()
        .regulation(existingRegulation)
        .branch(existingBranch)
        .semester(existingSemester)
        .subject(existingSubject)
        .build();
        srbs=srbsr.save(srbs);
        
        
        return existingSubject;
    }


    //Get subjects 
    @Override
    public List<Subject> getSubjectsByRegulationBranchSemester(Long regulationId, Long branchId, Long semesterId) {
        //Checking does regulation exist in regulation Id
        Regulation existingRegulation=rr.findById(regulationId).orElseThrow(()->
        new RuntimeException("Regulation deosn't exist on Regulation Id: "+regulationId));

        //Checking does Branch exist in Branch Id
        Branch existingBranch=br.findById(branchId).orElseThrow(()->
        new RuntimeException("Branch deosn't exist on Branch Id: "+branchId));


         //Checking does Semester exist in Semester Id
        Semester existingSemester=sr.findById(semesterId).orElseThrow(()->
        new RuntimeException("Semester deosn't exist on Semester Id: "+semesterId));
        List<Subject> subjects=srbsr.findSubjectsByRegulationBranchSemester(regulationId, branchId, semesterId);
        if(subjects==null)
            return List.of();

        return subjects;
    }


    //update Subject
    @Transactional
    @Override
    public Subject updateSubject(Long subjectId, Subject subject) {
        Subject existingSubject=subjectRepository.findById(subjectId).orElseThrow(()->
        new RuntimeException("Subject doesn't exist on Subjct Id: "+subjectId));
        
        if(subject.getCode()==null && subject.getName()==null)
            throw new RuntimeException("Subjct Name and Subject Code isn't given");

        if(subject.getCode()!=null)
            existingSubject.setCode(subject.getCode());
        if(subject.getName()!=null)
            existingSubject.setName(subject.getName());
        return existingSubject;
    }


    //Delete Subject
    @Transactional
    @Override
    public Subject deleteBySubjectId(Long subjectId) {
        
        Subject existingSubject=subjectRepository.findById(subjectId)
        .orElseThrow(()->new RuntimeException("Subject doesn't exist by Subjet Id: "+subjectId));
        

        srbsr.deleteBySubjectId(subjectId);
        List<Subject> subjects=List.of(existingSubject);
        
        pdfssr.deleteBySubjects(subjects);
        subjectRepository.deleteBySubjects(subjects);
        return existingSubject;
    } 


    
    


    
}
 
 