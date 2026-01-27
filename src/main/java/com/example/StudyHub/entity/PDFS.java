package com.example.StudyHub.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PDFS {

    @Id
    @SequenceGenerator(
        name = "pdfs_seq",
        sequenceName = "pdfs_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "pdfs_seq"
    )
    @Column(name = "pdfs_id")
    private Long id;

    @OneToMany(
        mappedBy = "pDFS",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<PDF> pdfs;
}
