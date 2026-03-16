package com.example.StudyHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudyHub.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name=:name")
    public User findByName(@Param("name")String name);
    
}
