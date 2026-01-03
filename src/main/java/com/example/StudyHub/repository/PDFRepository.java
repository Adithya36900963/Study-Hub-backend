package com.example.StudyHub.repository;

import com.example.StudyHub.entity.PDF;
import com.example.StudyHub.model.PDFModel;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PDFRepository extends JpaRepository<PDF, Long> {

    @Query("""
        SELECT new com.example.StudyHub.model.PDFModel(p.id, p.name)
        FROM PDF p
        WHERE p.pDFS.id = :pdfsId
    """)
    List<PDFModel> findPDFNames(@Param("pdfsId") Long pdfsId);

    @Query("SELECT p.data FROM PDF p WHERE p.id = :id")
    byte[] findPdfData(@Param("id") Long id);
}
