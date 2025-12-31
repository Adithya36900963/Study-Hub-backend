package com.example.StudyHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Regulation {
    
    @Id
    @Column(name="regulation_id")
    @SequenceGenerator(
    name = "regulation_seq",
    sequenceName = "regulation_seq",
    allocationSize = 1      
    )
    @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "regulation_seq"
    )
    private Long id;
    
    @Column(name="regulation_name")
    private String name;
    
}
