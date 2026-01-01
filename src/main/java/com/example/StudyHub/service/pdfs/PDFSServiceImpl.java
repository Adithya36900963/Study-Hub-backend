package com.example.StudyHub.service.pdfs;

import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.PDFS;

import com.example.StudyHub.repository.PDFSRepository;


@Service
public class PDFSServiceImpl implements PDFSServiceLayer {

    private PDFSRepository pdfsRepository;
    
    //Creation of Bean
    public PDFSServiceImpl(PDFSRepository pdfsRepository)
    {
        this.pdfsRepository=pdfsRepository;
    }

    //Creation pdfs IN table
    @Override
    public PDFS create() {
        return pdfsRepository.save(new PDFS());
    }

    @Override
    public PDFS save(PDFS pDFS) {
        
        return pdfsRepository.save(pDFS);
    }
   
}
