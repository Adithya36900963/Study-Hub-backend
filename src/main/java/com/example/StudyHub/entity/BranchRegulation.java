package com.example.StudyHub.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
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
    @UniqueConstraint(columnNames = {"regulation_id", "branch_id"})
  }
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BranchRegulation {

    @Id
    @SequenceGenerator(
        name = "branch_regulation_seq",
        sequenceName = "branch_regulation_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "branch_regulation_seq"
    )
    @Column(name="branch_regulation_id")
    private Long id;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regulation_id",
    referencedColumnName = "regulation_id",
    nullable = false)
    private Regulation regulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id",
    referencedColumnName = "branch_id",
    nullable = false)
    private Branch branch;

}
