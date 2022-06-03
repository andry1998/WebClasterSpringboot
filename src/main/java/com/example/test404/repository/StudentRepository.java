package com.example.test404.repository;

import com.example.test404.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select distinct study_group from students", nativeQuery = true)
    List<String> getStudyGroup();
}
