package com.example.StudyHub.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
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
    @Column(name = "pdf_id")
    private Long id;

    @Column(name = "pdf_name")
    private String name;

    @Lob
    @Column(name = "pdf_data")
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdfs_id", nullable = false)
    @JsonBackReference
    private PDFS pDFS;
}
