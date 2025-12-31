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
public class Semester {

    @Id
    @SequenceGenerator(
    name = "semester_seq",
    sequenceName = "semester_seq",
    allocationSize = 1      
    )
    @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "semester_seq"
    )
    @Column(name="semester_id")
    private Long id;

    @Column(name="semester_number")
    private byte number;
}
