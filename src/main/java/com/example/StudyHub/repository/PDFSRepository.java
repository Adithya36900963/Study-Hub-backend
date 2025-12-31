package com.example.StudyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.PDFS;

@Repository
public interface PDFSRepository extends JpaRepository<PDFS,Long> {
    
}
