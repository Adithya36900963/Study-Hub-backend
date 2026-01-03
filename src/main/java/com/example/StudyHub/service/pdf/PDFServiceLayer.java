package com.example.StudyHub.service.pdf;

import com.example.StudyHub.entity.PDF;

public interface PDFServiceLayer {
   byte[] getPdfData(Long id);

   PDF isExist(Long id);

}
