package com.example.StudyHub.service.pDFSRegulationSubject;

import com.example.StudyHub.entity.PDFS;

public interface PDFSRegulationSubjectsServiceLayer {

    PDFS isExist(Long regulationId, Long subjectId);

    
}
