package com.example.StudyHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PDF {
    
    @Id
    @SequenceGenerator(
        name = "pdf_seq",
        sequenceName = "pdf_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pdf_seq"
    )
    @Column(name="pdf_id")
    private Long id;

    @Column(name="pdf_name")
    private String name;

    @Lob
    @Column(name="pdf_data", columnDefinition="BLOB")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name="pdfs_id", nullable=false)
    private PDFS pdfs;
}
