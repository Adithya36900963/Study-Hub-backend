package com.example.StudyHub.service.pdfs;

import com.example.StudyHub.entity.PDFS;
import com.example.StudyHub.repository.PDFSRepository;
import org.springframework.stereotype.Service;

@Service
public class PDFSServiceImpl implements PDFSServiceLayer{

    private final PDFSRepository repo;

    public PDFSServiceImpl(PDFSRepository repo) {
        this.repo = repo;
    }

    public PDFS create() {
        return repo.save(new PDFS());
    }

    public PDFS save(PDFS pdfs) {
        return repo.save(pdfs);
    }
}
