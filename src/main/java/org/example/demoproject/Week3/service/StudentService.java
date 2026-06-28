package org.example.demoproject.Week3.service;

import org.example.demoproject.Week3.model.Student;
import org.example.demoproject.Week3.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student updated) {
        Student existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}