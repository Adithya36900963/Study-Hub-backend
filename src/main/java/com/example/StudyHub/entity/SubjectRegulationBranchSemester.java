package com.example.StudyHub.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"regulation_id", "branch_id", "semester_id", "subject_id"})
  }
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectRegulationBranchSemester {
    
    @Id
    @SequenceGenerator(
        name = "subject_reg_branch_sem_seq",
        sequenceName = "subject_reg_branch_sem_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE,
        generator="subject_reg_branch_sem_seq"
    )
  
    private Long id;

    @OneToOne
    @JoinColumn(name = "subject_id", nullable = false)  
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regulation_id",nullable = false,
    referencedColumnName = "regulation_id")
  
    private Regulation regulation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id",nullable = false,
    referencedColumnName = "branch_id")

    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id",nullable = false,
    referencedColumnName = "semester_id")
  
    private Semester semester;
}
