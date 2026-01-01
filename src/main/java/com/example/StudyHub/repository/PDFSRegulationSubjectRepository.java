package com.example.StudyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.PDFS;
import com.example.StudyHub.entity.PDFSRegulationSubject;

@Repository
public interface PDFSRegulationSubjectRepository extends JpaRepository<PDFSRegulationSubject,Long>{

    @Query("""
    SELECT prs.pdfs
    FROM PDFSRegulationSubject prs
    WHERE prs.regulation.id = :regulationId
      AND prs.subject.id = :subjectId
""")
    public PDFS isExist(@Param("regulationId") Long regulationId,@Param("subjectId") Long subjectId);
}
