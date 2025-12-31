package com.example.StudyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.PDF;

@Repository
public interface PDFRepoitory extends JpaRepository<PDF,Long> {
    
}
