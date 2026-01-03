package com.example.StudyHub.service.pDFSRegulationSubject;

import com.example.StudyHub.entity.PDFS;
import com.example.StudyHub.entity.PDFSRegulationSubject;
import com.example.StudyHub.repository.PDFSRegulationSubjectRepository;
import com.example.StudyHub.service.regulation.RegulationServiceLayer;
import com.example.StudyHub.service.subject.SubjectServiceLayer;

import org.springframework.stereotype.Service;

@Service
public class PDFSRegulationSubjectServiceImpl implements  PDFSRegulationSubjectsServiceLayer{

    private final PDFSRegulationSubjectRepository repo;
    private SubjectServiceLayer subjectServiceLayer;
    private RegulationServiceLayer regulationServiceLayer;

    public PDFSRegulationSubjectServiceImpl(PDFSRegulationSubjectRepository repo,
        RegulationServiceLayer regulationServiceLayer,
        SubjectServiceLayer subjectServiceLayer) {
        this.repo = repo;
        this.regulationServiceLayer=regulationServiceLayer;
        this.subjectServiceLayer =subjectServiceLayer;
    }

    public PDFS isExist(Long regulationId, Long subjectId) {
        PDFSRegulationSubject rs =
                repo.findByRegulationIdAndSubjectId(regulationId, subjectId);
        return rs != null ? rs.getPdfs() : null;
    }

    @Override
    public PDFSRegulationSubject create(Long regulationId, Long subjectId,PDFS pdfs) {

        PDFSRegulationSubject pdfrs=new PDFSRegulationSubject();
        pdfrs.setPdfs(pdfs);
        pdfrs.setRegulation(regulationServiceLayer.getRegulationById(regulationId));
        pdfrs.setSubject(subjectServiceLayer.getById(subjectId));
    
        return repo.save(pdfrs);
    }
}
