package com.example.test404.repository;

import com.example.test404.models.Person;
import com.example.test404.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    @Query(value = "SELECT distinct role FROM persons", nativeQuery = true)
    List<String> getRoles();

    @Query(value = "SELECT distinct status FROM persons", nativeQuery = true)
    List<String> getStatus();
}
