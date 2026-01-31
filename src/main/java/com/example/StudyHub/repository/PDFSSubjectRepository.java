package com.example.StudyHub.repository;


import com.example.StudyHub.entity.PDFSSubject;
import com.example.StudyHub.entity.Subject;
import com.example.StudyHub.model.PDFModel;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PDFSSubjectRepository extends JpaRepository<PDFSSubject, Long> {

        @Transactional
        @Modifying
        @Query("Delete from PDFSSubject pdfss where pdfss.subject in :subjects")
        void deleteBySubjects(@Param("subjects")List<Subject> subjects);

        @Query("Select pdfss from PDFSSubject pdfss where subject.id=:subjectId")
        PDFSSubject findBySubjectId(@Param("subjectId") Long subjectId);

        @Query("""
        SELECT new com.example.StudyHub.model.PDFModel(p.id, p.name)
        FROM PDFSSubject ps
        JOIN ps.pdfs pdfs
        JOIN pdfs.pdfs p
        WHERE ps.subject.id = :subjectId
        """)
        List<PDFModel> findPDFModelsBySubjectId(Long subjectId);

     
}
