package com.example.StudyHub.service.pDFSRegulationSubject;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.PDFS;

import com.example.StudyHub.repository.PDFSRegulationSubjectRepository;

@Service
public class PDFSRegulationSubjectServiceImpl implements PDFSRegulationSubjectsServiceLayer{

    private PDFSRegulationSubjectRepository pdfsRSR;

    //Creating a Bean for PDFSRegulationSubjectRepository
    PDFSRegulationSubjectServiceImpl(PDFSRegulationSubjectRepository pdfRSR)
    {
        this.pdfsRSR=pdfRSR;
    }

    //Is PDFS exist in PDFSREgulationSubject table 
    @Override
    public PDFS isExist(Long regulationId, Long subjectId) {
        return pdfsRSR.isExist(regulationId,subjectId);
    }

   
    
}
