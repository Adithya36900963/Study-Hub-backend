package com.example.StudyHub.service.pDFSRegulationSubject;

import com.example.StudyHub.entity.PDFS;
import com.example.StudyHub.entity.PDFSRegulationSubject;

public interface PDFSRegulationSubjectsServiceLayer {

    PDFS isExist(Long regulationId, Long subjectId);

    PDFSRegulationSubject create(Long regulationId, Long subjectId,PDFS pdfs);

    
}
