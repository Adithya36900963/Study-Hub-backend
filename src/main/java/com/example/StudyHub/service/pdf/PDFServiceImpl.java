package com.example.StudyHub.service.pdf;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.repository.PDFRepoitory;

@Service
public class PDFServiceImpl implements PDFServiceLayer {

    private PDFRepoitory pdfRepoitory;

    //Bean configuration
    PDFServiceImpl(PDFRepoitory pdfRepoitory)
    {
        this.pdfRepoitory=pdfRepoitory;
    }

    @Override
    public PDF save(PDF pdf) {
        
        return pdfRepoitory.save(pdf);
    }
    
}
