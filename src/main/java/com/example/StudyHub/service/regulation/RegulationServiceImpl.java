package com.example.StudyHub.service.regulation;

import java.util.List;



import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.Regulation;
import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.repository.BranchRegulationRepository;
import com.example.StudyHub.repository.PDFSSubjectRepository;
import com.example.StudyHub.repository.RegulationRepository;
import com.example.StudyHub.repository.SubjectRegulationBranchSemesterRepository;
import com.example.StudyHub.repository.SubjectRepository;

import jakarta.transaction.Transactional;

@Service
public class RegulationServiceImpl implements RegulationServiceLayer {

    /*
        1.One is Regulation Repository
        2.Remaining All are Service Layer
    */
    private RegulationRepository rr;
    private SubjectRegulationBranchSemesterRepository srbsr;
    private PDFSSubjectRepository pdfssr;
    private SubjectRepository sr;
    private BranchRegulationRepository brr;

    //Bean configration using Constructor
    public RegulationServiceImpl(RegulationRepository rr,
        SubjectRegulationBranchSemesterRepository srbsr,
        PDFSSubjectRepository pdfssr,
        SubjectRepository sr,
        BranchRegulationRepository brr
    )
    {
        this.rr = rr;
        this.srbsr=srbsr;
        this.pdfssr=pdfssr;
        this.sr=sr;
        this.brr=brr;
    }


    //Getting List of Regulations 
    @Override
    public List<Regulation> getRegulations() {
        List<Regulation> regulations=rr.findAll();
        if(regulations.isEmpty())
        {
            throw new RuntimeException
            ("Regulations list is null");
        }
        return regulations;
    }

    //Adding Regulation
    @Override
    public Regulation addRegulation(Regulation regulation) {
        if(regulation.getName()==null)
            throw new RuntimeException
            ("Regulation name isn't given");
        String name=regulation.getName().toUpperCase();
        regulation.setName(name);

        Regulation existingRegulation=rr.findByName(regulation.getName());
        if(existingRegulation!=null)
        {
            throw new RuntimeException
            ("Regulation exist's based on this name: "+regulation.getName());
        }
        
        return rr.save(regulation); 
    }

   /*
    This is Global Deletion of Regulation
        1.Delete Reguylation By Id 
        2.First Remove mappings from:
            1.srbssl
            2.brsl
   */
@Transactional
@Override
public Regulation deleteRegulationById(Long id) {

    Regulation r = rr.findById(id)
        .orElseThrow(() ->
            new RuntimeException("Regulation doesn't exist based on id: " + id));

    // 1. Fetch subjects FIRST 
    List<Subject> subjects = srbsr.findSubjectByRegulationId(id);

    // 2. Delete Subject-Regulation mappings
    srbsr.deleteByRegulationId(id);

    // 3. Delete PDFs & Subjects only if subjects exist
    if (!subjects.isEmpty()) {
        pdfssr.deleteBySubjects(subjects);
        sr.deleteBySubjects(subjects);
    }

    

    // 4. Delete Branch-Regulation mappings
    brr.deleteRegulationById(id);

    // 5. Finally delete Regulation
    rr.delete(r);

    return r;
}


    //Updation of Regulation name using @Transactional
    @Transactional
    @Override
    public Regulation updateRegulationById(Long id, Regulation regulation)
    {
        if(regulation.getName()==null)
            throw new RuntimeException
            ("Regulation name isn't given: ");

        Regulation r=rr.findById(id)
        .orElseThrow(()->new RuntimeException("Regulation doesn't exist based on id: " + id));

        
        String name=regulation.getName().toUpperCase();

        if(name.equals(r.getName()))
            throw new RuntimeException
            ("Regulation name is same as Previous: "+r.getName());
          
        Regulation existRegulationByName=rr.findByName(name);

        
        
        if(existRegulationByName!=null)
            throw new RuntimeException
            ("Regulation exist's based on this name: "+name);

       
        r.setName(name);
        return r;
    }


}
