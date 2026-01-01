package com.example.StudyHub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pdfs_id","subject_id","regulation_id"})
    }
)
public class PDFSRegulationSubject {
    
    @Id
    @SequenceGenerator(
        name = "pdfs_reg_subj_seq",
        sequenceName = "pdfs_reg_subj_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pdfs_reg_subj_seq"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdfs_id",
    referencedColumnName = "pdfs_id",
    nullable = false)
    private PDFS pdfs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regulation_id",
    referencedColumnName = "regulation_id",
    nullable = false)
    private Regulation regulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id",
    referencedColumnName = "subject_id",
    nullable = false)
    private Subject subject;
}
