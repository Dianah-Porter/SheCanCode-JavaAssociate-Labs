package org.example.demoproject.Week3_SpringOAuthGoogle.repository;

import org.example.demoproject.Week3_SpringOAuthGoogle.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}