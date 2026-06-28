package org.example.demoproject.Week3.repository;

import org.example.demoproject.Week3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}