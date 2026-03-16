package com.example.StudyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.Semester;

@Repository
public interface SemesterReposiory  extends JpaRepository<Semester,Long>{

}
