package com.example.StudyHub.repository;


import com.example.StudyHub.entity.PDFSRegulationSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PDFSRegulationSubjectRepository
        extends JpaRepository<PDFSRegulationSubject, Long> {

    PDFSRegulationSubject findByRegulationIdAndSubjectId(Long regulationId, Long subjectId);
}
