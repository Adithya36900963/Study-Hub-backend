package com.example.StudyHub.service.pdf;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.StudyHub.model.PDFModel;

public interface PDFServiceLayer {

   PDFModel createPDFSAndPDF(Long subjectId, String name, MultipartFile pdf) throws Exception;

   List<PDFModel> getPDFModel(Long subjectId);

   ResponseEntity<byte[]> downloadPDF(Long id);

}
