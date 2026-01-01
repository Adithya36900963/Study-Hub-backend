package com.example.StudyHub.entity;

import java.util.List;

import org.hibernate.annotations.CollectionId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @Column(name="pdfs_id")
    private Long id;

   @OneToMany(
    mappedBy = "pDFS",   // ✅ MUST MATCH CHILD FIELD NAME
    cascade = CascadeType.ALL
)
    private List<PDF> pdfs;
}

