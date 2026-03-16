package com.example.StudyHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "Users")
public class User {
    @Id
    @SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
    @GeneratedValue( strategy=GenerationType.SEQUENCE, generator="user_seq")

    private Long id;
    
    @Column(name="user_name",nullable=false,unique=true)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name="user_role",nullable = false)
    private Role role;
    @Column(name="user_password",nullable=false)
    private String password;


    public enum Role{
        SUPERVISOR,
        ADMIN
    }
}
