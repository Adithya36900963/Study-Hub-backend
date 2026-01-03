package com.example.StudyHub.controller;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.entity.PDFS;
import com.example.StudyHub.entity.PDFSRegulationSubject;
import com.example.StudyHub.model.PDFModel;
import com.example.StudyHub.repository.PDFRepository;
import com.example.StudyHub.service.pdf.PDFServiceLayer;
import com.example.StudyHub.service.pdfs.PDFSServiceLayer;
import com.example.StudyHub.service.pDFSRegulationSubject.PDFSRegulationSubjectsServiceLayer;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pdfs")
public class PDFController {

    private final PDFServiceLayer pdfService;
    private final PDFSServiceLayer pdfsService;
    private final PDFSRegulationSubjectsServiceLayer mapService;
    private final PDFRepository pdfRepo;

    public PDFController(
            PDFServiceLayer pdfService,
            PDFSServiceLayer pdfsService,
            PDFSRegulationSubjectsServiceLayer mapService,
            PDFRepository pdfRepo) {
        this.pdfService = pdfService;
        this.pdfsService = pdfsService;
        this.mapService = mapService;
        this.pdfRepo = pdfRepo;
    }

    // ✅ UPLOAD
    @Transactional
    @PostMapping(value = "/{regulationId}/{subjectId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PDFModel upload(
            @PathVariable Long regulationId,
            @PathVariable Long subjectId,
            @RequestParam String name,
            @RequestParam MultipartFile pdf) throws Exception {

        PDFS pdfs = mapService.isExist(regulationId, subjectId);
        if (pdfs == null)
        {
            pdfs = pdfsService.create();
            
            mapService.create(regulationId,subjectId,pdfs);
            
        }

        if (pdfs.getPdfs() == null)
            pdfs.setPdfs(new ArrayList<>());

        PDF entity = new PDF();
        entity.setName(name);
        entity.setData(pdf.getBytes());
        entity.setPDFS(pdfs);

        pdfs.getPdfs().add(entity);
        pdfsService.save(pdfs);
        
        return new PDFModel(entity.getId(), entity.getName());
    }


   

    // ✅ LIST
    @GetMapping("/{regulationId}/{subjectId}")
    public List<PDFModel> list(
            @PathVariable Long regulationId,
            @PathVariable Long subjectId) {

        PDFS pdfs = mapService.isExist(regulationId, subjectId);
        if (pdfs == null) return List.of();

        return pdfRepo.findPDFNames(pdfs.getId());
    }

    // ✅ DOWNLOAD
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {

        byte[] data = pdfService.getPdfData(id);
        if (data == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"file.pdf\"")
                .body(data);
    }
}
