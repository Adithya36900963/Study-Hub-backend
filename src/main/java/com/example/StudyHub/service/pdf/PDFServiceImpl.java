package com.example.StudyHub.service.pdf;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.repository.PDFRepository;
import org.springframework.stereotype.Service;

@Service
public class PDFServiceImpl implements PDFServiceLayer{

    private final PDFRepository repo;

    public PDFServiceImpl(PDFRepository repo) {
        this.repo = repo;
    }

    public byte[] getPdfData(Long id) {
        return repo.findPdfData(id);
    }

    public PDF isExist(Long id) {
        return repo.findById(id).orElse(null);
    }
}
