package com.example.StudyHub.service.pdf;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.entity.PDFS;
import com.example.StudyHub.entity.PDFSSubject;
import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.model.PDFModel;
import com.example.StudyHub.repository.PDFRepository;
import com.example.StudyHub.repository.PDFSRepository;
import com.example.StudyHub.repository.PDFSSubjectRepository;
import com.example.StudyHub.repository.SubjectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PDFServiceImpl implements PDFServiceLayer {

    private SubjectRepository subjectRepo;
    private PDFSRepository pdfsRepo;
    private PDFRepository pdfRepo;
    private PDFSSubjectRepository pdfssRepo;


    public PDFServiceImpl(
        SubjectRepository subjectRepo,
        PDFSRepository pdfsRepo,
        PDFRepository pdfRepo,
        PDFSSubjectRepository pdfssRepo
    ) {
       this.subjectRepo=subjectRepo; 
        this.pdfRepo = pdfRepo;
        this.pdfsRepo=pdfsRepo;
        this.pdfssRepo=pdfssRepo;
    }

    //Posting PDF
    @Override
    public PDFModel createPDFSAndPDF(Long subjectId, String name, MultipartFile pdf)  {

        Subject existingSubject=subjectRepo.findById(subjectId)
        .orElseThrow(()->new RuntimeException("Subject doen't exist on Subject Id: "+subjectId));

        

        PDFSSubject existingPDFSSubject=pdfssRepo.findBySubjectId(subjectId);
       
        try{
            if(existingPDFSSubject==null)
        {
            PDFS pdfs=new PDFS();
            PDFS newPdfs=pdfsRepo.save(pdfs);

            pdfssRepo.save(PDFSSubject.builder()
                                      .pdfs(newPdfs)
                                      .subject(existingSubject)
                                      .build());

            PDF creatingpdf=PDF.builder()
                        .name(name)
                        .data(pdf.getBytes())
                        .pDFS(pdfs)
                        .build();
            
            return PDFModel.builder()
                            .name(name)   
                            .id(pdfRepo.save(creatingpdf).getId())
                            .build(); 

        }
        PDF creatingpdf=PDF.builder()
                        .name(name)
                        .data(pdf.getBytes())
                        .pDFS(existingPDFSSubject.getPdfs())
                        .build();
        
        return PDFModel.builder()
                            .name(name)   
                            .id(pdfRepo.save(creatingpdf).getId())
                            .build(); 
        }catch(IOException e)
        {
            throw new RuntimeException("Failed to read PDF file bytes", e);
        }
    }

    //Get List of PDF Model
    @Override
    public List<PDFModel> getPDFModel(Long subjectId) {
        PDFS pdfs = pdfssRepo.findBySubjectId(subjectId).getPdfs();

        if (pdfs == null)
            return List.of();

        List<PDFModel> pdfmodel=new ArrayList<>();
        for(PDF p : pdfs.getPdfs())
        {
            pdfmodel.add(PDFModel.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .build());
        } 
        
        return pdfmodel;
    }

    @Override
    public ResponseEntity<byte[]> downloadPDF(Long id) {
        byte[] data = (pdfRepo.findById(id)
        .orElseThrow(()->new RuntimeException("PDF not found on PDF Id: "+id))).getData();
        

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"file.pdf\"")
                .body(data);
    }
    
}
