package org.example.demoproject.Week3_SpringOAuthGoogle.controller;

import org.example.demoproject.Week3_SpringOAuthGoogle.model.Student;
import org.example.demoproject.Week3_SpringOAuthGoogle.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping("/test")
    public String test() {
        return "API WORKS";
    }
    @PostMapping("/testpost")
    public String testPost() {
        return "POST WORKS";
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
