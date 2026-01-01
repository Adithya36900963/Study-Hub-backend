package com.example.StudyHub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.entity.PDFS;

import com.example.StudyHub.service.pDFSRegulationSubject.PDFSRegulationSubjectsServiceLayer;
import com.example.StudyHub.service.pdf.PDFServiceLayer;
import com.example.StudyHub.service.pdfs.PDFSServiceLayer;

import jakarta.persistence.Lob;

@RestController
@RequestMapping("/api/pdfs")
public class PDFController {
    
    private PDFServiceLayer pdfServiceLayer;
    private PDFSServiceLayer pdfsServiceLayer;
    private PDFSRegulationSubjectsServiceLayer pdfsrsl;

    //Bean Configuration
    public PDFController(PDFServiceLayer pdfServiceLayer,
        PDFSServiceLayer pdfsServiceLayer,
        PDFSRegulationSubjectsServiceLayer pdfsrsl)
    {
        this.pdfServiceLayer=pdfServiceLayer;
        this.pdfsServiceLayer=pdfsServiceLayer;
        this.pdfsrsl=pdfsrsl;
    }

    @GetMapping("/{regulationId}/{subjectId}")
    public List<String> getPDFNames(
            @PathVariable Long regulationId,
            @PathVariable Long subjectId) {

        PDFS rs = pdfsrsl.isExist(regulationId, subjectId);

        if (rs == null || rs.getPdfs() == null) {
            return List.of(); // ✅ []
        }

        return rs.getPdfs()
                .stream()
                .map(PDF::getName)   // or getTitle(), getFileName()
                .toList();
    }


    @GetMapping("/{regulationId}/{subjectId}/{pdfId}")
public ResponseEntity<byte[]> getPDF(
        @PathVariable Long regulationId,
        @PathVariable Long subjectId,
        @PathVariable Long pdfId) {

    PDFS rs = pdfsrsl.isExist(regulationId, subjectId);

    if (rs == null) {
        return ResponseEntity.notFound().build();
    }

    PDF pdf = rs.getPdfs()
                .stream()
                .filter(p -> p.getId().equals(pdfId))
                .findFirst()
                .orElse(null);

    if (pdf == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
            .header("Content-Type", "application/pdf")
            .header("Content-Disposition",
                    "inline; filename=\"" + pdf.getName() + "\"")
            .body(pdf.getData()); // byte[]
}

}